package com.groom.product.application.service

import com.groom.platform.saga.SagaTrackerClient
import com.groom.product.common.annotation.UnitTest
import com.groom.product.domain.model.Product
import com.groom.product.domain.port.LoadProductPort
import com.groom.product.domain.port.SaveProductPort
import com.groom.product.domain.port.StockReservationPort
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import java.math.BigDecimal
import java.util.UUID
import java.util.concurrent.TimeUnit

@UnitTest
class StockReservationServiceTest :
    BehaviorSpec({

        val testStoreId = UUID.randomUUID()
        val testCategoryId = UUID.randomUUID()

        fun createTestProduct(
            id: UUID,
            stockQuantity: Int,
        ) = Product(
            id = id,
            storeId = testStoreId,
            storeName = "테스트 스토어",
            categoryId = testCategoryId,
            name = "테스트 상품",
            price = BigDecimal("10000"),
            stockQuantity = stockQuantity,
        )

        Given("재고 예약을 시도할 때") {

            When("재고가 충분한 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val loadProductPort = mockk<LoadProductPort>(relaxed = true)
                val saveProductPort = mockk<SaveProductPort>(relaxed = true)
                val sagaTrackerClient = mockk<SagaTrackerClient>(relaxed = true)
                val stockService = StockReservationService(stockReservationPort, loadProductPort, saveProductPort, sagaTrackerClient)

                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val items = listOf(StockReservationService.OrderItem(testProductId, 10))
                val product = createTestProduct(testProductId, 100)

                every { loadProductPort.loadAllById(listOf(testProductId)) } returns listOf(product)
                every { stockReservationPort.getOrInitializeStock(testProductId, 100) } returns 100L
                every { stockReservationPort.decrementStock(testProductId, 10) } returns 90L
                every { stockReservationPort.saveReservation(testOrderId, testProductId, 10, 15, TimeUnit.MINUTES) } just runs

                Then("예약에 성공하고 재고 차감 및 예약 저장이 호출된다") {
                    val result = stockService.reserveStock(testOrderId, items)

                    result.shouldBeInstanceOf<StockReservationService.ReservationResult.Success>()
                    val successResult = result as StockReservationService.ReservationResult.Success
                    successResult.reservedItems.size shouldBe 1
                    successResult.reservedItems[0].productId shouldBe testProductId
                    successResult.reservedItems[0].quantity shouldBe 10
                    successResult.reservedItems[0].reservedStock shouldBe 90

                    verify { stockReservationPort.getOrInitializeStock(testProductId, 100) }
                    verify { stockReservationPort.decrementStock(testProductId, 10) }
                    verify { stockReservationPort.saveReservation(testOrderId, testProductId, 10, 15, TimeUnit.MINUTES) }
                }
            }

            When("재고가 부족한 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val loadProductPort = mockk<LoadProductPort>(relaxed = true)
                val saveProductPort = mockk<SaveProductPort>(relaxed = true)
                val sagaTrackerClient = mockk<SagaTrackerClient>(relaxed = true)
                val stockService = StockReservationService(stockReservationPort, loadProductPort, saveProductPort, sagaTrackerClient)

                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val items = listOf(StockReservationService.OrderItem(testProductId, 150))
                val product = createTestProduct(testProductId, 100)

                every { loadProductPort.loadAllById(listOf(testProductId)) } returns listOf(product)
                every { stockReservationPort.getOrInitializeStock(testProductId, 100) } returns 100L
                every { stockReservationPort.decrementStock(testProductId, 150) } returns -50L
                every { stockReservationPort.incrementStock(testProductId, 150) } returns 100L

                Then("예약에 실패하고 롤백된다") {
                    val result = stockService.reserveStock(testOrderId, items)

                    result.shouldBeInstanceOf<StockReservationService.ReservationResult.Failure>()
                    val failureResult = result as StockReservationService.ReservationResult.Failure
                    failureResult.failedItems.size shouldBe 1
                    failureResult.failedItems[0].productId shouldBe testProductId
                    failureResult.failedItems[0].requestedQuantity shouldBe 150
                    failureResult.failedItems[0].availableStock shouldBe 100
                    failureResult.reason shouldBe "재고 부족"

                    verify { stockReservationPort.incrementStock(testProductId, 150) }
                }
            }

            When("상품이 존재하지 않는 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val loadProductPort = mockk<LoadProductPort>(relaxed = true)
                val saveProductPort = mockk<SaveProductPort>(relaxed = true)
                val sagaTrackerClient = mockk<SagaTrackerClient>(relaxed = true)
                val stockService = StockReservationService(stockReservationPort, loadProductPort, saveProductPort, sagaTrackerClient)

                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val items = listOf(StockReservationService.OrderItem(testProductId, 10))

                every { loadProductPort.loadAllById(listOf(testProductId)) } returns emptyList()

                Then("예약에 실패한다") {
                    val result = stockService.reserveStock(testOrderId, items)

                    result.shouldBeInstanceOf<StockReservationService.ReservationResult.Failure>()
                    val failureResult = result as StockReservationService.ReservationResult.Failure
                    failureResult.failedItems.size shouldBe 1
                    failureResult.failedItems[0].productId shouldBe testProductId
                    failureResult.failedItems[0].availableStock shouldBe 0
                    failureResult.reason shouldBe "상품을 찾을 수 없습니다"
                }
            }

            When("여러 상품 중 하나라도 재고가 부족한 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val loadProductPort = mockk<LoadProductPort>(relaxed = true)
                val saveProductPort = mockk<SaveProductPort>(relaxed = true)
                val sagaTrackerClient = mockk<SagaTrackerClient>(relaxed = true)
                val stockService = StockReservationService(stockReservationPort, loadProductPort, saveProductPort, sagaTrackerClient)

                val testOrderId = UUID.randomUUID()
                val testProductId1 = UUID.randomUUID()
                val testProductId2 = UUID.randomUUID()
                val items =
                    listOf(
                        StockReservationService.OrderItem(testProductId1, 10),
                        StockReservationService.OrderItem(testProductId2, 200),
                    )
                val product1 = createTestProduct(testProductId1, 100)
                val product2 =
                    Product(
                        id = testProductId2,
                        storeId = testStoreId,
                        storeName = "테스트 스토어",
                        categoryId = testCategoryId,
                        name = "테스트 상품2",
                        price = BigDecimal("20000"),
                        stockQuantity = 50,
                    )

                every { loadProductPort.loadAllById(listOf(testProductId1, testProductId2)) } returns listOf(product1, product2)
                every { stockReservationPort.getOrInitializeStock(testProductId1, 100) } returns 100L
                every { stockReservationPort.decrementStock(testProductId1, 10) } returns 90L
                every { stockReservationPort.saveReservation(testOrderId, testProductId1, 10, 15, TimeUnit.MINUTES) } just runs
                every { stockReservationPort.getOrInitializeStock(testProductId2, 50) } returns 50L
                every { stockReservationPort.decrementStock(testProductId2, 200) } returns -150L
                every { stockReservationPort.incrementStock(testProductId2, 200) } returns 50L
                every { stockReservationPort.incrementStock(testProductId1, 10) } returns 100L
                every { stockReservationPort.deleteReservation(testOrderId, testProductId1) } just runs

                Then("전체 예약이 롤백되고 첫 번째 상품의 예약도 롤백된다") {
                    val result = stockService.reserveStock(testOrderId, items)

                    result.shouldBeInstanceOf<StockReservationService.ReservationResult.Failure>()
                    verify { stockReservationPort.incrementStock(testProductId1, 10) }
                    verify { stockReservationPort.deleteReservation(testOrderId, testProductId1) }
                }
            }
        }

        Given("재고 확정을 시도할 때") {

            When("재고가 충분한 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val loadProductPort = mockk<LoadProductPort>(relaxed = true)
                val saveProductPort = mockk<SaveProductPort>(relaxed = true)
                val sagaTrackerClient = mockk<SagaTrackerClient>(relaxed = true)
                val stockService = StockReservationService(stockReservationPort, loadProductPort, saveProductPort, sagaTrackerClient)

                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val items = listOf(StockReservationService.OrderItem(testProductId, 10))
                val product = createTestProduct(testProductId, 100)

                every { loadProductPort.loadById(testProductId) } returns product
                every { saveProductPort.save(any()) } returns product
                every { stockReservationPort.deleteReservation(testOrderId, testProductId) } just runs

                Then("확정에 성공하고 DB 저장 및 예약 삭제가 호출된다") {
                    val result = stockService.confirmStock(testOrderId, items)

                    result shouldBe true
                    verify { saveProductPort.save(any()) }
                    verify { stockReservationPort.deleteReservation(testOrderId, testProductId) }
                }
            }

            When("상품이 존재하지 않는 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val loadProductPort = mockk<LoadProductPort>(relaxed = true)
                val saveProductPort = mockk<SaveProductPort>(relaxed = true)
                val sagaTrackerClient = mockk<SagaTrackerClient>(relaxed = true)
                val stockService = StockReservationService(stockReservationPort, loadProductPort, saveProductPort, sagaTrackerClient)

                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val items = listOf(StockReservationService.OrderItem(testProductId, 10))

                every { loadProductPort.loadById(testProductId) } returns null

                Then("확정에 실패한다") {
                    val result = stockService.confirmStock(testOrderId, items)
                    result shouldBe false
                }
            }

            When("DB 재고가 부족한 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val loadProductPort = mockk<LoadProductPort>(relaxed = true)
                val saveProductPort = mockk<SaveProductPort>(relaxed = true)
                val sagaTrackerClient = mockk<SagaTrackerClient>(relaxed = true)
                val stockService = StockReservationService(stockReservationPort, loadProductPort, saveProductPort, sagaTrackerClient)

                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val items = listOf(StockReservationService.OrderItem(testProductId, 200))
                val product = createTestProduct(testProductId, 100)

                every { loadProductPort.loadById(testProductId) } returns product

                Then("확정에 실패한다") {
                    val result = stockService.confirmStock(testOrderId, items)
                    result shouldBe false
                }
            }
        }
    })

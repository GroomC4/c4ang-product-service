package com.groom.product.domain.service

import com.groom.product.adapter.outbound.persistence.ProductJpaRepository
import com.groom.product.common.annotation.UnitTest
import com.groom.product.domain.model.Product
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.redisson.api.RAtomicLong
import org.redisson.api.RBucket
import org.redisson.api.RedissonClient
import java.math.BigDecimal
import java.util.Optional
import java.util.UUID

@UnitTest
@DisplayName("StockReservationService 단위 테스트")
class StockReservationServiceTest {
    private lateinit var redissonClient: RedissonClient
    private lateinit var productRepository: ProductJpaRepository
    private lateinit var stockService: StockReservationService

    private val testOrderId = UUID.randomUUID()
    private val testProductId1 = UUID.randomUUID()
    private val testProductId2 = UUID.randomUUID()
    private val testStoreId = UUID.randomUUID()
    private val testCategoryId = UUID.randomUUID()

    @BeforeEach
    fun setup() {
        redissonClient = mockk(relaxed = true)
        productRepository = mockk(relaxed = true)
        stockService = StockReservationService(redissonClient, productRepository)
    }

    @Nested
    @DisplayName("Given: 재고 예약을 시도할 때")
    inner class ReserveStockTests {
        @Test
        @DisplayName("When: 재고가 충분한 경우 > Then: 예약에 성공한다")
        fun `should reserve stock successfully when stock is sufficient`() {
            // Given
            val items =
                listOf(
                    StockReservationService.OrderItem(testProductId1, 10),
                )

            val product =
                Product(
                    id = testProductId1,
                    storeId = testStoreId,
                    storeName = "테스트 스토어",
                    categoryId = testCategoryId,
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            every { productRepository.findAllById(listOf(testProductId1)) } returns listOf(product)

            val atomicLong = mockk<RAtomicLong>(relaxed = true)
            every { atomicLong.isExists } returns false
            every { atomicLong.addAndGet(-10L) } returns 90L
            every { redissonClient.getAtomicLong(any()) } returns atomicLong

            val bucket = mockk<RBucket<Int>>(relaxed = true)
            every { redissonClient.getBucket<Int>(any()) } returns bucket

            // When
            val result = stockService.reserveStock(testOrderId, items)

            // Then
            result.shouldBeInstanceOf<StockReservationService.ReservationResult.Success>()
            val successResult = result as StockReservationService.ReservationResult.Success
            successResult.reservedItems.size shouldBe 1
            successResult.reservedItems[0].productId shouldBe testProductId1
            successResult.reservedItems[0].quantity shouldBe 10
            successResult.reservedItems[0].reservedStock shouldBe 90

            verify { atomicLong.set(100L) }
            verify { atomicLong.addAndGet(-10L) }
            verify { bucket.set(10, 15, any()) }
        }

        @Test
        @DisplayName("When: 재고가 부족한 경우 > Then: 예약에 실패하고 롤백된다")
        fun `should fail reservation and rollback when stock is insufficient`() {
            // Given
            val items =
                listOf(
                    StockReservationService.OrderItem(testProductId1, 150),
                )

            val product =
                Product(
                    id = testProductId1,
                    storeId = testStoreId,
                    storeName = "테스트 스토어",
                    categoryId = testCategoryId,
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            every { productRepository.findAllById(listOf(testProductId1)) } returns listOf(product)

            val atomicLong = mockk<RAtomicLong>(relaxed = true)
            every { atomicLong.isExists } returns false
            every { atomicLong.addAndGet(-150L) } returns -50L
            every { atomicLong.addAndGet(150L) } returns 100L // 롤백
            every { redissonClient.getAtomicLong(any()) } returns atomicLong

            // When
            val result = stockService.reserveStock(testOrderId, items)

            // Then
            result.shouldBeInstanceOf<StockReservationService.ReservationResult.Failure>()
            val failureResult = result as StockReservationService.ReservationResult.Failure
            failureResult.failedItems.size shouldBe 1
            failureResult.failedItems[0].productId shouldBe testProductId1
            failureResult.failedItems[0].requestedQuantity shouldBe 150
            // availableStock = remainingStock + requested = -50 + 150 = 100
            failureResult.failedItems[0].availableStock shouldBe 100
            failureResult.reason shouldBe "재고 부족"

            verify { atomicLong.addAndGet(-150L) }
            verify { atomicLong.addAndGet(150L) } // 롤백 확인
        }

        @Test
        @DisplayName("When: 상품이 존재하지 않는 경우 > Then: 예약에 실패한다")
        fun `should fail reservation when product not found`() {
            // Given
            val items =
                listOf(
                    StockReservationService.OrderItem(testProductId1, 10),
                )

            every { productRepository.findAllById(listOf(testProductId1)) } returns emptyList()

            // When
            val result = stockService.reserveStock(testOrderId, items)

            // Then
            result.shouldBeInstanceOf<StockReservationService.ReservationResult.Failure>()
            val failureResult = result as StockReservationService.ReservationResult.Failure
            failureResult.failedItems.size shouldBe 1
            failureResult.failedItems[0].productId shouldBe testProductId1
            failureResult.failedItems[0].availableStock shouldBe 0
            failureResult.reason shouldBe "상품을 찾을 수 없습니다"
        }

        @Test
        @DisplayName("When: 여러 상품 중 하나라도 재고가 부족한 경우 > Then: 전체 예약이 롤백된다")
        fun `should rollback all reservations when one product fails`() {
            // Given
            val items =
                listOf(
                    StockReservationService.OrderItem(testProductId1, 10),
                    StockReservationService.OrderItem(testProductId2, 200), // 재고 부족
                )

            val product1 =
                Product(
                    id = testProductId1,
                    storeId = testStoreId,
                    storeName = "테스트 스토어",
                    categoryId = testCategoryId,
                    name = "테스트 상품1",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

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

            every { productRepository.findAllById(listOf(testProductId1, testProductId2)) } returns
                listOf(
                    product1,
                    product2,
                )

            val atomicLong1 = mockk<RAtomicLong>(relaxed = true)
            val atomicLong2 = mockk<RAtomicLong>(relaxed = true)

            every { atomicLong1.isExists } returns false
            every { atomicLong1.addAndGet(-10L) } returns 90L
            every { atomicLong1.addAndGet(10L) } returns 100L // 롤백

            every { atomicLong2.isExists } returns false
            every { atomicLong2.addAndGet(-200L) } returns -150L

            every { redissonClient.getAtomicLong("stock:$testProductId1") } returns atomicLong1
            every { redissonClient.getAtomicLong("stock:$testProductId2") } returns atomicLong2

            val bucket = mockk<RBucket<Int>>(relaxed = true)
            every { redissonClient.getBucket<Int>(any()) } returns bucket

            // When
            val result = stockService.reserveStock(testOrderId, items)

            // Then
            result.shouldBeInstanceOf<StockReservationService.ReservationResult.Failure>()

            // product1의 예약이 롤백되었는지 확인
            verify { atomicLong1.addAndGet(10L) }
            verify { bucket.delete() }
        }
    }

    @Nested
    @DisplayName("Given: 재고 확정을 시도할 때")
    inner class ConfirmStockTests {
        @Test
        @DisplayName("When: 재고가 충분한 경우 > Then: 확정에 성공한다")
        fun `should confirm stock successfully`() {
            // Given
            val items =
                listOf(
                    StockReservationService.OrderItem(testProductId1, 10),
                )

            val product =
                Product(
                    id = testProductId1,
                    storeId = testStoreId,
                    storeName = "테스트 스토어",
                    categoryId = testCategoryId,
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            every { productRepository.findById(testProductId1) } returns Optional.of(product)
            every { productRepository.save(any()) } returns product

            val bucket = mockk<RBucket<Int>>(relaxed = true)
            every { redissonClient.getBucket<Int>(any()) } returns bucket

            // When
            val result = stockService.confirmStock(testOrderId, items)

            // Then
            result shouldBe true
            verify { productRepository.save(any()) }
            verify { bucket.delete() }
        }

        @Test
        @DisplayName("When: 상품이 존재하지 않는 경우 > Then: 확정에 실패한다")
        fun `should fail confirmation when product not found`() {
            // Given
            val items =
                listOf(
                    StockReservationService.OrderItem(testProductId1, 10),
                )

            every { productRepository.findById(testProductId1) } returns Optional.empty()

            // When
            val result = stockService.confirmStock(testOrderId, items)

            // Then
            result shouldBe false
        }

        @Test
        @DisplayName("When: DB 재고가 부족한 경우 > Then: 확정에 실패한다")
        fun `should fail confirmation when DB stock is insufficient`() {
            // Given
            val items =
                listOf(
                    StockReservationService.OrderItem(testProductId1, 200),
                )

            val product =
                Product(
                    id = testProductId1,
                    storeId = testStoreId,
                    storeName = "테스트 스토어",
                    categoryId = testCategoryId,
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            every { productRepository.findById(testProductId1) } returns Optional.of(product)

            // When
            val result = stockService.confirmStock(testOrderId, items)

            // Then
            result shouldBe false
        }
    }
}

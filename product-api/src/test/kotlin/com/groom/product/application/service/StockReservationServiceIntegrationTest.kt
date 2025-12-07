package com.groom.product.application.service

import com.groom.product.adapter.outbound.ai.TestGeminiConfig
import com.groom.product.common.IntegrationTestBase
import com.groom.product.common.config.NoOpEventPublisherConfig
import com.groom.product.common.config.TestAwsConfig
import com.groom.product.domain.model.Product
import com.groom.product.domain.port.LoadProductPort
import com.groom.product.domain.port.SaveProductPort
import com.groom.product.domain.port.StockReservationPort
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.redisson.api.RedissonClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.UUID

@Import(NoOpEventPublisherConfig::class, TestAwsConfig::class, TestGeminiConfig::class)
@DisplayName("StockReservationService 통합 테스트")
class StockReservationServiceIntegrationTest : IntegrationTestBase() {
    @Autowired
    private lateinit var stockReservationService: StockReservationService

    @Autowired
    private lateinit var stockReservationPort: StockReservationPort

    @Autowired
    private lateinit var saveProductPort: SaveProductPort

    @Autowired
    private lateinit var loadProductPort: LoadProductPort

    @Autowired
    private lateinit var redissonClient: RedissonClient

    private val testStoreId = UUID.randomUUID()
    private val testCategoryId = UUID.randomUUID()

    @BeforeEach
    fun setUp() {
        // Redis 키 정리
        redissonClient.keys.getKeysByPattern("stock:*").forEach { key ->
            redissonClient.getBucket<Any>(key).delete()
        }
    }

    private fun createAndSaveProduct(stockQuantity: Int): Product {
        val product =
            Product(
                storeId = testStoreId,
                storeName = "테스트 스토어",
                categoryId = testCategoryId,
                name = "테스트 상품 ${UUID.randomUUID()}",
                price = BigDecimal("10000"),
                stockQuantity = stockQuantity,
            )
        return saveProductPort.save(product)
    }

    @Nested
    @DisplayName("재고 예약 통합 테스트")
    inner class ReserveStockIntegrationTest {
        @Test
        @Transactional
        @DisplayName("재고가 충분할 때 예약에 성공한다")
        fun `should reserve stock successfully when stock is sufficient`() {
            // Given
            val product = createAndSaveProduct(100)
            val orderId = UUID.randomUUID()
            val items = listOf(StockReservationService.OrderItem(product.id, 10))

            // When
            val result = stockReservationService.reserveStock(orderId, items)

            // Then
            assertThat(result).isInstanceOf(StockReservationService.ReservationResult.Success::class.java)
            val successResult = result as StockReservationService.ReservationResult.Success
            assertThat(successResult.reservedItems).hasSize(1)
            assertThat(successResult.reservedItems[0].productId).isEqualTo(product.id)
            assertThat(successResult.reservedItems[0].quantity).isEqualTo(10)
            assertThat(successResult.reservedItems[0].reservedStock).isEqualTo(90)

            // Redis에 예약 정보가 저장되었는지 확인
            val reservation = stockReservationPort.getReservation(orderId, product.id)
            assertThat(reservation).isEqualTo(10)
        }

        @Test
        @Transactional
        @DisplayName("재고가 부족할 때 예약에 실패한다")
        fun `should fail reservation when stock is insufficient`() {
            // Given
            val product = createAndSaveProduct(50)
            val orderId = UUID.randomUUID()
            val items = listOf(StockReservationService.OrderItem(product.id, 100))

            // When
            val result = stockReservationService.reserveStock(orderId, items)

            // Then
            assertThat(result).isInstanceOf(StockReservationService.ReservationResult.Failure::class.java)
            val failureResult = result as StockReservationService.ReservationResult.Failure
            assertThat(failureResult.reason).isEqualTo("재고 부족")
            assertThat(failureResult.failedItems[0].availableStock).isEqualTo(50)

            // Redis에 예약 정보가 없어야 함 (롤백됨)
            val reservation = stockReservationPort.getReservation(orderId, product.id)
            assertThat(reservation).isNull()
        }

        @Test
        @Transactional
        @DisplayName("존재하지 않는 상품 예약 시 실패한다")
        fun `should fail reservation when product does not exist`() {
            // Given
            val orderId = UUID.randomUUID()
            val nonExistentProductId = UUID.randomUUID()
            val items = listOf(StockReservationService.OrderItem(nonExistentProductId, 10))

            // When
            val result = stockReservationService.reserveStock(orderId, items)

            // Then
            assertThat(result).isInstanceOf(StockReservationService.ReservationResult.Failure::class.java)
            val failureResult = result as StockReservationService.ReservationResult.Failure
            assertThat(failureResult.reason).isEqualTo("상품을 찾을 수 없습니다")
        }

        @Test
        @Transactional
        @DisplayName("여러 상품 예약 시 하나라도 실패하면 전체 롤백된다")
        fun `should rollback all when one product fails in multi-product reservation`() {
            // Given
            val product1 = createAndSaveProduct(100)
            val product2 = createAndSaveProduct(30) // 적은 재고
            val orderId = UUID.randomUUID()
            val items =
                listOf(
                    StockReservationService.OrderItem(product1.id, 10),
                    StockReservationService.OrderItem(product2.id, 50), // 재고 부족
                )

            // When
            val result = stockReservationService.reserveStock(orderId, items)

            // Then
            assertThat(result).isInstanceOf(StockReservationService.ReservationResult.Failure::class.java)

            // 첫 번째 상품도 롤백되어 예약이 없어야 함
            val reservation1 = stockReservationPort.getReservation(orderId, product1.id)
            assertThat(reservation1).isNull()

            // Redis 재고도 원래대로 복구되었는지 확인
            val currentStock = stockReservationPort.getOrInitializeStock(product1.id, 100)
            assertThat(currentStock).isEqualTo(100)
        }
    }

    @Nested
    @DisplayName("재고 확정 통합 테스트")
    inner class ConfirmStockIntegrationTest {
        @Test
        @Transactional
        @DisplayName("예약된 재고 확정에 성공한다")
        fun `should confirm reserved stock successfully`() {
            // Given: 먼저 예약 수행
            val product = createAndSaveProduct(100)
            val orderId = UUID.randomUUID()
            val items = listOf(StockReservationService.OrderItem(product.id, 10))

            val reserveResult = stockReservationService.reserveStock(orderId, items)
            assertThat(reserveResult).isInstanceOf(StockReservationService.ReservationResult.Success::class.java)

            // When: 확정 수행
            val confirmResult = stockReservationService.confirmStock(orderId, items)

            // Then
            assertThat(confirmResult).isTrue()

            // DB 재고가 차감되었는지 확인
            val updatedProduct = loadProductPort.loadById(product.id)
            assertThat(updatedProduct?.stockQuantity).isEqualTo(90)

            // Redis 예약이 삭제되었는지 확인
            val reservation = stockReservationPort.getReservation(orderId, product.id)
            assertThat(reservation).isNull()
        }

        @Test
        @Transactional
        @DisplayName("존재하지 않는 상품 확정 시 실패한다")
        fun `should fail confirmation when product does not exist`() {
            // Given
            val orderId = UUID.randomUUID()
            val nonExistentProductId = UUID.randomUUID()
            val items = listOf(StockReservationService.OrderItem(nonExistentProductId, 10))

            // When
            val result = stockReservationService.confirmStock(orderId, items)

            // Then
            assertThat(result).isFalse()
        }
    }

    @Nested
    @DisplayName("만료 인덱스 통합 테스트")
    inner class ExpiryIndexIntegrationTest {
        @Test
        @Transactional
        @DisplayName("예약 시 만료 인덱스에 등록된다")
        fun `should register expiry when reserving stock`() {
            // Given
            val product = createAndSaveProduct(100)
            val orderId = UUID.randomUUID()
            val items = listOf(StockReservationService.OrderItem(product.id, 10))

            // When
            stockReservationService.reserveStock(orderId, items)

            // Then: 미래 시간으로 만료 엔트리 조회 (현재 시간 + 충분한 시간)
            val futureTime = System.currentTimeMillis() / 1000 + 3600 // 1시간 후
            val expiredEntries = stockReservationPort.getExpiredEntries(futureTime)

            assertThat(expiredEntries).anyMatch { entry ->
                entry.contains(orderId.toString()) && entry.contains(product.id.toString())
            }
        }
    }
}

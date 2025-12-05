package com.groom.product.application.service

import com.groom.product.domain.port.LoadProductPort
import com.groom.product.domain.port.SaveProductPort
import com.groom.product.domain.port.StockReservationPort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.UUID
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

/**
 * 재고 예약 Application 서비스
 *
 * Domain과 Port를 오케스트레이션하여 재고 예약 유스케이스를 수행합니다.
 * - Redis Atomic Operations를 통한 동시성 제어
 * - DB 재고 차감은 결제 완료 시 수행 (재고 확정)
 */
@Service
class StockReservationService(
    private val stockReservationPort: StockReservationPort,
    private val loadProductPort: LoadProductPort,
    private val saveProductPort: SaveProductPort,
) {
    /**
     * 주문 상품들의 재고를 예약합니다.
     *
     * @param orderId 주문 ID
     * @param items 주문 아이템 리스트 (productId, quantity)
     * @param ttlMinutes 예약 만료 시간 (기본 15분)
     * @return 성공 시 예약된 아이템 정보, 실패 시 null
     */
    @Transactional(readOnly = true)
    fun reserveStock(
        orderId: UUID,
        items: List<OrderItem>,
        ttlMinutes: Long = 15,
    ): ReservationResult {
        logger.info { "📦 Attempting to reserve stock for orderId: $orderId, items: $items" }

        // 1. DB에서 상품 재고 확인
        val productIds = items.map { it.productId }
        val products = loadProductPort.loadAllById(productIds)

        if (products.size != items.size) {
            val foundIds = products.map { it.id }.toSet()
            val missingIds = productIds.filterNot { it in foundIds }
            logger.warn { "⚠️  Products not found: $missingIds" }
            return ReservationResult.Failure(
                failedItems =
                    items
                        .filter { it.productId in missingIds }
                        .map {
                            FailedItem(
                                productId = it.productId,
                                requestedQuantity = it.quantity,
                                availableStock = 0,
                            )
                        },
                reason = "상품을 찾을 수 없습니다",
            )
        }

        // 2. Redis에서 원자적으로 재고 예약
        val reservedItems = mutableListOf<ReservedItem>()
        val failedItems = mutableListOf<FailedItem>()

        for (item in items) {
            val product = products.first { it.id == item.productId }

            // Redis에서 현재 재고 확인 (없으면 DB 값으로 초기화)
            stockReservationPort.getOrInitializeStock(item.productId, product.stockQuantity)

            // 원자적 재고 차감
            val remainingStock = stockReservationPort.decrementStock(item.productId, item.quantity)

            if (remainingStock < 0) {
                // 재고 부족 - 롤백
                stockReservationPort.incrementStock(item.productId, item.quantity)
                logger.warn {
                    "⚠️  Insufficient stock for product ${item.productId}: requested=${item.quantity}, available=${remainingStock + item.quantity}"
                }

                failedItems.add(
                    FailedItem(
                        productId = item.productId,
                        requestedQuantity = item.quantity,
                        availableStock = (remainingStock + item.quantity).toInt(),
                    ),
                )
                // 하나라도 실패하면 전체 롤백
                break
            } else {
                // 성공 - 예약 정보 저장
                stockReservationPort.saveReservation(
                    orderId = orderId,
                    productId = item.productId,
                    quantity = item.quantity,
                    ttl = ttlMinutes,
                    timeUnit = TimeUnit.MINUTES,
                )

                // 만료 인덱스에 등록 (스케줄러가 만료된 예약을 처리할 수 있도록)
                val expiresAtEpochSecond = Instant.now().plusSeconds(ttlMinutes * 60).epochSecond
                stockReservationPort.registerExpiry(
                    orderId = orderId,
                    productId = item.productId,
                    quantity = item.quantity,
                    expiresAtEpochSecond = expiresAtEpochSecond,
                )

                reservedItems.add(
                    ReservedItem(
                        productId = item.productId,
                        quantity = item.quantity,
                        reservedStock = remainingStock.toInt(),
                    ),
                )
            }
        }

        // 3. 실패 시 전체 롤백
        if (failedItems.isNotEmpty()) {
            rollbackReservation(orderId, reservedItems)
            return ReservationResult.Failure(
                failedItems = failedItems,
                reason = "재고 부족",
            )
        }

        logger.info { "✅ Stock reserved successfully for orderId: $orderId, reservedItems: $reservedItems" }
        return ReservationResult.Success(reservedItems)
    }

    /**
     * 재고 예약을 확정합니다 (결제 완료 시).
     *
     * Redis의 임시 예약을 제거하고, DB에서 실제 재고를 차감합니다.
     *
     * @param orderId 주문 ID
     * @param items 확정할 아이템 리스트
     */
    @Transactional
    fun confirmStock(
        orderId: UUID,
        items: List<OrderItem>,
    ): Boolean {
        logger.info { "📦 Confirming stock for orderId: $orderId, items: $items" }

        try {
            for (item in items) {
                val product =
                    loadProductPort.loadById(item.productId)
                        ?: throw IllegalArgumentException("Product not found: ${item.productId}")

                // DB 재고 차감
                try {
                    product.decreaseStock(item.quantity)
                    saveProductPort.save(product)
                } catch (e: IllegalArgumentException) {
                    logger.error { "❌ DB stock became negative for product ${item.productId}: ${e.message}" }
                    return false
                }

                // Redis 예약 정보 삭제
                stockReservationPort.deleteReservation(orderId, item.productId)
            }

            logger.info { "✅ Stock confirmed for orderId: $orderId" }
            return true
        } catch (e: Exception) {
            logger.error(e) { "❌ Failed to confirm stock for orderId: $orderId" }
            return false
        }
    }

    /**
     * 재고 예약을 롤백합니다.
     *
     * @param orderId 주문 ID
     * @param reservedItems 롤백할 아이템 리스트
     */
    private fun rollbackReservation(
        orderId: UUID,
        reservedItems: List<ReservedItem>,
    ) {
        logger.warn { "🔄 Rolling back stock reservation for orderId: $orderId" }

        for (item in reservedItems) {
            stockReservationPort.incrementStock(item.productId, item.quantity)
            stockReservationPort.deleteReservation(orderId, item.productId)
        }
    }

    data class OrderItem(
        val productId: UUID,
        val quantity: Int,
    )

    data class ReservedItem(
        val productId: UUID,
        val quantity: Int,
        val reservedStock: Int,
    )

    data class FailedItem(
        val productId: UUID,
        val requestedQuantity: Int,
        val availableStock: Int,
    )

    sealed class ReservationResult {
        data class Success(
            val reservedItems: List<ReservedItem>,
        ) : ReservationResult()

        data class Failure(
            val failedItems: List<FailedItem>,
            val reason: String,
        ) : ReservationResult()
    }
}

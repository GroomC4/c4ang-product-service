package com.groom.product.adapter.outbound.event

import com.groom.ecommerce.product.event.avro.ReservedItem
import com.groom.ecommerce.product.event.avro.StockReserved
import com.groom.ecommerce.saga.event.avro.FailedItem
import com.groom.ecommerce.saga.event.avro.StockReservationFailed
import com.groom.product.domain.service.StockReservationService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * Stock 관련 이벤트를 Kafka에 발행하는 Producer
 *
 * Product Service가 발행하는 이벤트:
 * - stock.reserved: 재고 예약 성공
 * - stock.reservation.failed: 재고 예약 실패
 */
@Component
class StockEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
) {
    /**
     * stock.reserved 이벤트 발행
     *
     * @param orderId 주문 ID (파티션 키로 사용)
     * @param reservedItems 예약된 아이템 목록
     */
    fun publishStockReserved(
        orderId: UUID,
        reservedItems: List<StockReservationService.ReservedItem>,
    ) {
        val event =
            StockReserved
                .newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setEventTimestamp(Instant.now().toEpochMilli())
                .setOrderId(orderId.toString())
                .setReservedItems(
                    reservedItems.map { item ->
                        ReservedItem
                            .newBuilder()
                            .setProductId(item.productId.toString())
                            .setQuantity(item.quantity)
                            .setReservedStock(item.reservedStock)
                            .build()
                    },
                ).setReservedAt(Instant.now().toEpochMilli())
                .build()

        kafkaTemplate.send("stock.reserved", orderId.toString(), event)
            .whenComplete { result, ex ->
                if (ex == null) {
                    logger.info { "✅ Published stock.reserved event - orderId: $orderId, offset: ${result?.recordMetadata?.offset()}" }
                } else {
                    logger.error(ex) { "❌ Failed to publish stock.reserved event - orderId: $orderId" }
                }
            }
    }

    /**
     * stock.reservation.failed 이벤트 발행
     *
     * @param orderId 주문 ID (파티션 키로 사용)
     * @param failedItems 실패한 아이템 목록
     * @param reason 실패 이유
     */
    fun publishStockReservationFailed(
        orderId: UUID,
        failedItems: List<StockReservationService.FailedItem>,
        reason: String,
    ) {
        val event =
            StockReservationFailed
                .newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setEventTimestamp(Instant.now().toEpochMilli())
                .setOrderId(orderId.toString())
                .setFailedItems(
                    failedItems.map { item ->
                        FailedItem
                            .newBuilder()
                            .setProductId(item.productId.toString())
                            .setRequestedQuantity(item.requestedQuantity)
                            .setAvailableStock(item.availableStock)
                            .build()
                    },
                ).setFailureReason(reason)
                .setFailedAt(Instant.now().toEpochMilli())
                .build()

        kafkaTemplate.send("stock.reservation.failed", orderId.toString(), event)
            .whenComplete { result, ex ->
                if (ex == null) {
                    logger.info { "✅ Published stock.reservation.failed event - orderId: $orderId, offset: ${result?.recordMetadata?.offset()}" }
                } else {
                    logger.error(ex) { "❌ Failed to publish stock.reservation.failed event - orderId: $orderId" }
                }
            }
    }
}

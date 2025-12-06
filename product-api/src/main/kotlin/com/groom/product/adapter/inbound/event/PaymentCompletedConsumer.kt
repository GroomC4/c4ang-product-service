package com.groom.product.adapter.inbound.event

import com.groom.ecommerce.order.event.avro.ConfirmedOrderItem
import com.groom.ecommerce.order.event.avro.StockConfirmed
import com.groom.ecommerce.payment.event.avro.PaymentCompleted
import com.groom.ecommerce.saga.event.avro.StockConfirmationFailed
import com.groom.product.adapter.outbound.persistence.ProcessedEventRepository
import com.groom.product.application.service.StockReservationService
import com.groom.product.domain.model.ProcessedEvent
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * PaymentCompleted 이벤트 Consumer
 *
 * Payment Service에서 발행된 결제 완료 이벤트를 소비하여
 * 예약된 재고를 확정합니다 (Redis → DB 영구 저장).
 *
 * Topic: payment.completed
 * Event Schema: PaymentCompleted.avsc
 *
 * Success: stock.confirmed 이벤트 발행
 * Failure: stock.confirmation.failed 이벤트 발행
 */
@Component
class PaymentCompletedConsumer(
    private val stockService: StockReservationService,
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    private val processedEventRepository: ProcessedEventRepository,
) {
    @KafkaListener(
        topics = ["payment.completed"],
        groupId = "product-service",
        containerFactory = "kafkaListenerContainerFactory",
    )
    @Transactional
    fun consume(
        @Payload event: PaymentCompleted,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        acknowledgment: Acknowledgment,
    ) {
        val eventId = event.eventId.toString()
        val orderId = UUID.fromString(event.orderId.toString())
        val paymentId = event.paymentId.toString()

        logger.info { "📨 Received payment.completed event - eventId: $eventId, orderId: $orderId, paymentId: $paymentId" }

        // 멱등성 체크: 이미 처리된 이벤트인지 확인
        if (processedEventRepository.existsByEventId(eventId)) {
            logger.warn { "⚠️  Event already processed - eventId: $eventId. Skipping." }
            acknowledgment.acknowledge()
            return
        }

        try {
            // Redis의 만료 인덱스에서 해당 orderId에 대한 예약 정보를 조회합니다.
            // 만료 인덱스에는 orderId:productId:quantity 형식으로 저장되어 있습니다.
            val items = stockService.getReservedItems(orderId)

            if (items.isEmpty()) {
                logger.warn { "⚠️  No reserved items found for orderId: $orderId. Skipping stock confirmation." }
                // 예약 정보가 없으면 이미 처리되었거나 만료된 것으로 간주
                processedEventRepository.save(
                    ProcessedEvent(
                        eventId = eventId,
                        eventType = "payment.completed",
                    ),
                )
                acknowledgment.acknowledge()
                return
            }

            logger.info { "📦 Found ${items.size} reserved items for orderId: $orderId" }

            // 재고 확정 (Redis → DB)
            val success = stockService.confirmStock(orderId, items)

            if (success) {
                logger.info { "✅ Stock confirmation successful for orderId: $orderId" }

                // stock.confirmed 이벤트 발행
                publishStockConfirmed(orderId, paymentId, items)
            } else {
                logger.warn { "⚠️  Stock confirmation failed for orderId: $orderId" }

                // stock.confirmation.failed 이벤트 발행
                publishStockConfirmationFailed(orderId, paymentId, "재고 확정 실패")
            }

            // 처리 완료 기록
            processedEventRepository.save(
                ProcessedEvent(
                    eventId = eventId,
                    eventType = "payment.completed",
                ),
            )

            // Kafka manual commit
            acknowledgment.acknowledge()
        } catch (e: Exception) {
            logger.error(e) { "❌ Failed to process payment.completed event - eventId: $eventId" }
            throw e // 재처리를 위해 예외 던지기
        }
    }

    private fun publishStockConfirmed(
        orderId: UUID,
        paymentId: String,
        items: List<StockReservationService.OrderItem>,
    ) {
        val event =
            StockConfirmed
                .newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setEventTimestamp(Instant.now().toEpochMilli())
                .setOrderId(orderId.toString())
                .setPaymentId(paymentId)
                .setConfirmedItems(
                    items.map { item ->
                        ConfirmedOrderItem
                            .newBuilder()
                            .setProductId(item.productId.toString())
                            .setQuantity(item.quantity)
                            .build()
                    },
                ).setConfirmedAt(Instant.now().toEpochMilli())
                .build()

        kafkaTemplate
            .send("stock.confirmed", orderId.toString(), event)
            .whenComplete { result, ex ->
                if (ex == null) {
                    logger.info { "✅ Published stock.confirmed event - orderId: $orderId, offset: ${result?.recordMetadata?.offset()}" }
                } else {
                    logger.error(ex) { "❌ Failed to publish stock.confirmed event - orderId: $orderId" }
                }
            }
    }

    private fun publishStockConfirmationFailed(
        orderId: UUID,
        paymentId: String,
        reason: String,
    ) {
        val event =
            StockConfirmationFailed
                .newBuilder()
                .setEventId(UUID.randomUUID().toString())
                .setEventTimestamp(Instant.now().toEpochMilli())
                .setOrderId(orderId.toString())
                .setPaymentId(paymentId)
                .setFailureReason(reason)
                .setFailedAt(Instant.now().toEpochMilli())
                .build()

        kafkaTemplate
            .send("stock.confirmation.failed", orderId.toString(), event)
            .whenComplete { result, ex ->
                if (ex == null) {
                    logger.info {
                        "✅ Published stock.confirmation.failed event - orderId: $orderId, offset: ${result?.recordMetadata?.offset()}"
                    }
                } else {
                    logger.error(ex) { "❌ Failed to publish stock.confirmation.failed event - orderId: $orderId" }
                }
            }
    }
}

package com.groom.product.adapter.inbound.event

import com.groom.ecommerce.order.event.avro.OrderCreated
import com.groom.product.adapter.outbound.event.StockEventProducer
import com.groom.product.adapter.outbound.persistence.ProcessedEventRepository
import com.groom.product.application.service.StockReservationService
import com.groom.product.domain.model.ProcessedEvent
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * OrderCreated 이벤트 Consumer
 *
 * Order Service에서 발행된 주문 생성 이벤트를 소비하여
 * 재고를 예약하고 결과 이벤트를 발행합니다.
 *
 * Topic: order.created
 * Event Schema: OrderCreated.avsc
 *
 * Success: stock.reserved 이벤트 발행
 * Failure: stock.reservation.failed 이벤트 발행
 */
@Component
class OrderCreatedConsumer(
    private val stockService: StockReservationService,
    private val stockEventProducer: StockEventProducer,
    private val processedEventRepository: ProcessedEventRepository,
) {
    @KafkaListener(
        topics = ["order.created"],
        groupId = "product-service",
        containerFactory = "kafkaListenerContainerFactory",
    )
    @Transactional
    fun consume(
        @Payload event: OrderCreated,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        acknowledgment: Acknowledgment,
    ) {
        val eventId = event.eventId.toString()
        val orderId = UUID.fromString(event.orderId.toString())

        logger.info { "📨 Received order.created event - eventId: $eventId, orderId: $orderId" }

        // 멱등성 체크: 이미 처리된 이벤트인지 확인
        if (processedEventRepository.existsByEventId(eventId)) {
            logger.warn { "⚠️  Event already processed - eventId: $eventId. Skipping." }
            acknowledgment.acknowledge()
            return
        }

        try {
            // OrderCreated 이벤트에서 아이템 추출
            val items =
                event.items.map { item ->
                    StockReservationService.OrderItem(
                        productId = UUID.fromString(item.productId.toString()),
                        quantity = item.quantity,
                    )
                }

            // 재고 예약 시도
            when (val result = stockService.reserveStock(orderId, items)) {
                is StockReservationService.ReservationResult.Success -> {
                    logger.info { "✅ Stock reservation successful for orderId: $orderId" }

                    // stock.reserved 이벤트 발행
                    stockEventProducer.publishStockReserved(
                        orderId = orderId,
                        reservedItems = result.reservedItems,
                    )
                }

                is StockReservationService.ReservationResult.Failure -> {
                    logger.warn { "⚠️  Stock reservation failed for orderId: $orderId, reason: ${result.reason}" }

                    // stock.reservation.failed 이벤트 발행
                    stockEventProducer.publishStockReservationFailed(
                        orderId = orderId,
                        failedItems = result.failedItems,
                        reason = result.reason,
                    )
                }
            }

            // 처리 완료 기록
            processedEventRepository.save(
                ProcessedEvent(
                    eventId = eventId,
                    eventType = "order.created",
                ),
            )

            // Kafka manual commit
            acknowledgment.acknowledge()
        } catch (e: Exception) {
            logger.error(e) { "❌ Failed to process order.created event - eventId: $eventId" }
            throw e // 재처리를 위해 예외 던지기
        }
    }
}

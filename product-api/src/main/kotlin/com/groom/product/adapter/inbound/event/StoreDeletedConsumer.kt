package com.groom.product.adapter.inbound.event

import com.groom.ecommerce.store.event.avro.StoreDeleted
import com.groom.product.adapter.outbound.persistence.ProcessedEventRepository
import com.groom.product.adapter.outbound.persistence.ProductJpaRepository
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
 * StoreDeleted 이벤트 Consumer
 *
 * Store Service에서 발행된 스토어 삭제 이벤트를 소비하여
 * 해당 스토어의 모든 상품을 소프트 삭제합니다.
 *
 * Topic: store.deleted
 * Event Schema: StoreDeleted.avsc
 */
@Component
class StoreDeletedConsumer(
    private val productRepository: ProductJpaRepository,
    private val processedEventRepository: ProcessedEventRepository,
) {
    @KafkaListener(
        topics = ["store.deleted"],
        groupId = "product-service",
        containerFactory = "kafkaListenerContainerFactory",
    )
    @Transactional
    fun consume(
        @Payload event: StoreDeleted,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        acknowledgment: Acknowledgment,
    ) {
        val eventId = event.eventId.toString()

        logger.info { "📨 Received store.deleted event - eventId: $eventId, storeId: ${event.storeId}" }

        // 멱등성 체크: 이미 처리된 이벤트인지 확인
        if (processedEventRepository.existsByEventId(eventId)) {
            logger.warn { "⚠️  Event already processed - eventId: $eventId. Skipping." }
            acknowledgment.acknowledge()
            return
        }

        try {
            val storeId = UUID.fromString(event.storeId.toString())

            // 스토어의 모든 상품 조회
            val products = productRepository.findByStoreId(storeId)

            logger.info { "🔍 Found ${products.size} products for deleted store - storeId: $storeId" }

            // 모든 상품 소프트 삭제
            var deletedCount = 0
            products.forEach { product ->
                if (product.delete()) {
                    deletedCount++
                }
            }

            logger.info { "✅ Soft deleted $deletedCount products for deleted store - storeId: $storeId" }

            // 처리 완료 기록
            processedEventRepository.save(
                ProcessedEvent(
                    eventId = eventId,
                    eventType = "store.deleted",
                ),
            )

            // Kafka manual commit
            acknowledgment.acknowledge()
        } catch (e: Exception) {
            logger.error(e) { "❌ Failed to process store.deleted event - eventId: $eventId" }
            throw e // 재처리를 위해 예외 던지기
        }
    }
}

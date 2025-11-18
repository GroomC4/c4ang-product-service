package com.groom.product.adapter.inbound.event

import com.groom.ecommerce.store.event.avro.StoreInfoUpdated
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
 * StoreInfoUpdated 이벤트 Consumer
 *
 * Store Service에서 발행된 스토어 정보 변경 이벤트를 소비하여
 * Product 테이블의 비정규화된 스토어 정보를 동기화합니다.
 *
 * Topic: store.info.updated
 * Event Schema: StoreInfoUpdated.avsc
 */
@Component
class StoreInfoUpdatedConsumer(
    private val productRepository: ProductJpaRepository,
    private val processedEventRepository: ProcessedEventRepository,
) {
    @KafkaListener(
        topics = ["store.info.updated"],
        groupId = "product-service",
        containerFactory = "kafkaListenerContainerFactory",
    )
    @Transactional
    fun consume(
        @Payload event: StoreInfoUpdated,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String,
        acknowledgment: Acknowledgment,
    ) {
        val eventId = event.eventId.toString()

        logger.info { "📨 Received store.info.updated event - eventId: $eventId, storeId: ${event.storeId}, storeName: ${event.storeName}" }

        // 멱등성 체크: 이미 처리된 이벤트인지 확인
        if (processedEventRepository.existsByEventId(eventId)) {
            logger.warn { "⚠️  Event already processed - eventId: $eventId. Skipping." }
            acknowledgment.acknowledge()
            return
        }

        try {
            val storeId = UUID.fromString(event.storeId.toString())
            val storeName = event.storeName.toString()

            // 비정규화된 스토어 이름 업데이트
            val updatedCount = productRepository.bulkUpdateStoreName(storeId, storeName)

            logger.info { "✅ Updated $updatedCount products with new store name - storeId: $storeId, storeName: $storeName" }

            // 처리 완료 기록
            processedEventRepository.save(
                ProcessedEvent(
                    eventId = eventId,
                    eventType = "store.info.updated",
                ),
            )

            // Kafka manual commit
            acknowledgment.acknowledge()
        } catch (e: Exception) {
            logger.error(e) { "❌ Failed to process store.info.updated event - eventId: $eventId" }
            throw e // 재처리를 위해 예외 던지기
        }
    }
}

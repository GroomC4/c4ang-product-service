package com.groom.product.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

/**
 * 중복 이벤트 처리 방지를 위한 엔티티
 *
 * Kafka 이벤트의 멱등성을 보장하기 위해 사용됩니다.
 * 동일한 eventId를 가진 이벤트가 재처리되는 것을 방지합니다.
 */
@Entity
@Table(name = "p_processed_event")
class ProcessedEvent(
    @Id
    @Column(nullable = false, length = 255)
    val eventId: String,
    @Column(nullable = false, length = 100)
    val eventType: String,
    @Column(nullable = false)
    val processedAt: Instant = Instant.now(),
)

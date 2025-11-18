package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.ProcessedEvent
import org.springframework.data.jpa.repository.JpaRepository

interface ProcessedEventRepository : JpaRepository<ProcessedEvent, String> {
    fun existsByEventId(eventId: String): Boolean
}

package com.groom.product.domain.event

import com.groom.product.domain.event.DomainEvent
import java.time.LocalDateTime
import java.util.UUID

/**
 * 상품 복원 이벤트.
 */
data class ProductRestoredEvent(
    val productId: UUID,
    val storeId: UUID,
    val name: String,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
) : DomainEvent

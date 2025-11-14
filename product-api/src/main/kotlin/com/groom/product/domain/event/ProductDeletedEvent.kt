package com.groom.product.domain.event

import com.groom.product.common.domain.DomainEvent
import java.time.LocalDateTime
import java.util.UUID

/**
 * 상품이 삭제되었을 때 발생하는 도메인 이벤트.
 */
data class ProductDeletedEvent(
    val productId: UUID,
    val storeId: UUID,
    val name: String,
    val deletedAt: LocalDateTime,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
) : DomainEvent

package com.groom.product.domain.event

import com.groom.product.common.domain.DomainEvent
import java.time.LocalDateTime
import java.util.UUID

/**
 * 상품 숨김 처리 이벤트.
 */
data class ProductHiddenEvent(
    val productId: UUID,
    val storeId: UUID,
    val name: String,
    val hiddenAt: LocalDateTime,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
) : DomainEvent

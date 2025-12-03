package com.groom.product.domain.event

import com.groom.product.domain.event.DomainEvent
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

/**
 * 상품 정보가 수정되었을 때 발생하는 도메인 이벤트.
 */
data class ProductInfoUpdatedEvent(
    val productId: UUID,
    val storeId: UUID,
    val categoryId: UUID,
    val name: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val description: String?,
    val thumbnailUrl: String?,
    val status: String,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
) : DomainEvent

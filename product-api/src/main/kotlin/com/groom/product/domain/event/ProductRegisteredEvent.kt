package com.groom.product.domain.event

import com.groom.product.domain.event.DomainEvent
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

/**
 * 상품 등록 도메인 이벤트.
 *
 * 새로운 상품이 등록되었을 때 발생합니다.
 * 감사 로깅을 위해 사용됩니다.
 */
data class ProductRegisteredEvent(
    val productId: UUID,
    val storeId: UUID,
    val categoryId: UUID?,
    val name: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val status: String,
    val hiddenAt: LocalDateTime?,
    override val occurredAt: LocalDateTime = LocalDateTime.now(),
) : DomainEvent

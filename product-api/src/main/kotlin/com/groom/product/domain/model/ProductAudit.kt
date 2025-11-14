package com.groom.product.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

/**
 * ProductAudit 엔티티.
 * DDL: p_product_audit 테이블
 *
 * 상품 등록/숨김/삭제 등 변동 내역을 기록하는 감사 로그입니다.
 */
@Entity
@Table(name = "p_product_audit")
class ProductAudit(
    @Column(nullable = false)
    val productId: UUID,
    @Column
    val actorUserId: UUID?,
    @Column(nullable = false)
    val eventType: String, // PRODUCT_REGISTERED, PRODUCT_HIDDEN, PRODUCT_DELETED, PRODUCT_UPDATED
    @Column
    val statusSnapshot: String?, // ON_SALE, OFF_SHELF
    @Column
    val hiddenAtSnapshot: LocalDateTime?,
    @Column(columnDefinition = "text")
    val changeSummary: String?,
    @Column(nullable = false)
    val recordedAt: LocalDateTime = LocalDateTime.now(),
    @Column(columnDefinition = "jsonb")
    val metadata: String? = null,
) {
    @Id
    @Column(columnDefinition = "uuid", updatable = false)
    var id: UUID = UUID.randomUUID()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductAudit) return false
        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: System.identityHashCode(this)

    override fun toString(): String = "ProductAudit(id=$id, productId=$productId, eventType=$eventType)"
}

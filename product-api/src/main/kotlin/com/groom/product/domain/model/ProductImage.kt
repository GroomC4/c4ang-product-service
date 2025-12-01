package com.groom.product.domain.model

import com.groom.product.configuration.jpa.CreatedAndUpdatedAtAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

/**
 * ProductImage 엔티티.
 * DDL: p_product_image 테이블
 */
@Entity
@Table(name = "p_product_image")
class ProductImage(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val imageType: String, // PRIMARY, DETAIL
    @Column(nullable = false)
    val imageUrl: String,
    @Column(nullable = false)
    val displayOrder: Int = 0,
    @Column
    val deletedAt: LocalDateTime? = null,
) : CreatedAndUpdatedAtAuditEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductImage) return false
        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: System.identityHashCode(this)

    override fun toString(): String = "ProductImage(id=$id, imageType=$imageType, displayOrder=$displayOrder)"
}

package com.groom.product.domain.model

import com.groom.product.configuration.jpa.CreatedAndUpdatedAtAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

/**
 * ProductCategory 엔티티.
 * DDL: p_product_category 테이블 (계층 구조)
 */
@Entity
@Table(name = "p_product_category")
class ProductCategory(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val name: String,
    @Column
    val parentCategoryId: UUID? = null,
    @Column(nullable = false)
    val depth: Int = 0,
    @Column
    val deletedAt: LocalDateTime? = null,
) : CreatedAndUpdatedAtAuditEntity() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductCategory) return false
        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: System.identityHashCode(this)

    override fun toString(): String = "ProductCategory(id=$id, name=$name, depth=$depth)"
}

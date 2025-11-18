package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.ProductAudit
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * ProductAudit JPA Repository.
 */
interface ProductAuditJpaRepository : JpaRepository<ProductAudit, UUID> {
    fun findByProductId(productId: UUID): List<ProductAudit>
}

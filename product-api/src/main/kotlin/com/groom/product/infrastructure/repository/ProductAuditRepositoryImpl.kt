package com.groom.product.infrastructure.repository

import com.groom.product.domain.model.ProductAudit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductAuditRepositoryImpl : JpaRepository<ProductAudit, UUID> {
    fun findByProductId(productId: UUID): List<ProductAudit>
}

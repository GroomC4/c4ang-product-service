package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.ProductImage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * ProductImage JPA Repository.
 */
interface ProductImageJpaRepository : JpaRepository<ProductImage, UUID> {
    fun findByProduct_Id(productId: UUID): List<ProductImage>

    fun findByProduct_IdAndImageType(
        productId: UUID,
        imageType: String,
    ): List<ProductImage>

    fun findByProduct_IdOrderByDisplayOrderAsc(productId: UUID): List<ProductImage>
}

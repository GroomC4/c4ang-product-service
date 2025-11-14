package com.groom.product.infrastructure.repository

import com.groom.product.domain.model.ProductImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductImageRepositoryImpl : JpaRepository<ProductImage, UUID> {
    fun findByProduct_Id(productId: UUID): List<ProductImage>

    fun findByProduct_IdAndImageType(
        productId: UUID,
        imageType: String,
    ): List<ProductImage>

    fun findByProduct_IdOrderByDisplayOrderAsc(productId: UUID): List<ProductImage>
}

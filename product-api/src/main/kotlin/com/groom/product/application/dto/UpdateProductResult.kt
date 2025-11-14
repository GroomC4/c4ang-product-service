package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 상품 수정 Result DTO.
 */
data class UpdateProductResult(
    val productId: String,
    val storeId: String,
    val categoryId: String,
    val name: String,
    val status: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val thumbnailUrl: String?,
    val description: String?,
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(product: Product): UpdateProductResult =
            UpdateProductResult(
                productId = product.id.toString(),
                storeId = product.storeId.toString(),
                categoryId = product.categoryId.toString(),
                name = product.name,
                status = product.status,
                price = product.price,
                stockQuantity = product.stockQuantity,
                thumbnailUrl = product.thumbnailUrl,
                description = product.description,
                updatedAt = product.updatedAt ?: LocalDateTime.now(),
            )
    }
}

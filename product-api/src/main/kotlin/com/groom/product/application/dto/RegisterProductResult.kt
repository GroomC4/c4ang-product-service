package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 상품 등록 Result DTO.
 *
 * 등록된 상품의 정보를 반환합니다.
 */
data class RegisterProductResult(
    val productId: String,
    val storeId: String,
    val categoryId: String,
    val name: String,
    val status: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val thumbnailUrl: String?,
    val description: String?,
    val createdAt: LocalDateTime,
    val images: List<ProductImageInfo>,
) {
    data class ProductImageInfo(
        val imageType: String,
        val imageUrl: String,
        val displayOrder: Int,
    )

    companion object {
        fun from(product: Product): RegisterProductResult =
            RegisterProductResult(
                productId = product.id.toString(),
                storeId = product.storeId.toString(),
                categoryId = product.categoryId.toString(),
                name = product.name,
                status = product.status,
                price = product.price,
                stockQuantity = product.stockQuantity,
                thumbnailUrl = product.thumbnailUrl,
                description = product.description,
                createdAt = product.createdAt ?: java.time.LocalDateTime.now(),
                images =
                    product.images.map { image ->
                        ProductImageInfo(
                            imageType = image.imageType,
                            imageUrl = image.imageUrl,
                            displayOrder = image.displayOrder,
                        )
                    },
            )
    }
}

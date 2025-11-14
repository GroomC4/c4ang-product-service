package com.groom.product.application.dto

import java.math.BigDecimal
import java.util.UUID

/**
 * 상품 등록 Command DTO.
 *
 * 판매자가 새로운 상품을 등록할 때 사용합니다.
 */
data class RegisterProductCommand(
    val storeId: UUID,
    val categoryId: UUID,
    val name: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val thumbnailUrl: String?,
    val description: String?,
    val images: List<ProductImageDto> = emptyList(),
    val useAiDescription: Boolean = false,
) {
    data class ProductImageDto(
        val imageType: String, // PRIMARY, DETAIL
        val imageUrl: String,
        val displayOrder: Int,
    )
}

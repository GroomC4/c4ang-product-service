package com.groom.product.application.dto

import java.math.BigDecimal
import java.util.UUID

/**
 * 상품 수정 Command DTO.
 */
data class UpdateProductCommand(
    val productId: UUID,
    val storeId: UUID,
    val name: String,
    val price: BigDecimal,
    val stockQuantity: Int,
    val description: String?,
    val thumbnailUrl: String?,
    val categoryId: UUID,
)

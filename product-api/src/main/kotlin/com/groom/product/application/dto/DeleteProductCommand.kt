package com.groom.product.application.dto

import java.util.UUID

/**
 * 상품 삭제 Command DTO.
 */
data class DeleteProductCommand(
    val productId: UUID,
    val storeId: UUID,
)

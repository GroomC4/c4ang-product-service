package com.groom.product.application.dto

import java.util.UUID

/**
 * 상품 숨김/복원 Command DTO.
 */
data class ToggleProductHideCommand(
    val productId: UUID,
    val storeId: UUID,
)

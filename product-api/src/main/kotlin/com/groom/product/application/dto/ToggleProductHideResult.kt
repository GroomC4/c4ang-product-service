package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import java.time.LocalDateTime

/**
 * 상품 숨김/복원 Result DTO.
 */
data class ToggleProductHideResult(
    val productId: String,
    val storeId: String,
    val name: String,
    val isHidden: Boolean,
    val hiddenAt: LocalDateTime?,
) {
    companion object {
        fun from(product: Product): ToggleProductHideResult =
            ToggleProductHideResult(
                productId = product.id.toString(),
                storeId = product.storeId.toString(),
                name = product.name,
                isHidden = product.hiddenAt != null,
                hiddenAt = product.hiddenAt,
            )
    }
}

package com.groom.product.adapter.`in`.web.dto

import com.groom.product.application.dto.ToggleProductHideCommand
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

/**
 * 상품 숨김/복원 요청 DTO.
 */
@Schema(description = "상품 숨김/복원 요청")
data class ToggleProductHideRequest(
    @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    val storeId: String,
) {
    fun toCommand(productId: UUID): ToggleProductHideCommand =
        ToggleProductHideCommand(
            productId = productId,
            storeId = UUID.fromString(storeId),
        )
}

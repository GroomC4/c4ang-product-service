package com.groom.product.adapter.inbound.web.dto

import com.groom.product.application.dto.ToggleProductHideResult
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 상품 숨김/복원 응답 DTO.
 */
@Schema(description = "상품 숨김/복원 응답")
data class ToggleProductHideResponse(
    @Schema(description = "상품 ID", example = "123e4567-e89b-12d3-a456-426614174002")
    val productId: String,
    @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val storeId: String,
    @Schema(description = "상품명", example = "무선 마우스")
    val name: String,
    @Schema(description = "숨김 여부", example = "true")
    val isHidden: Boolean,
    @Schema(description = "숨김 처리 일시", example = "2025-01-15T13:30:00")
    val hiddenAt: LocalDateTime?,
) {
    companion object {
        fun from(result: ToggleProductHideResult): ToggleProductHideResponse =
            ToggleProductHideResponse(
                productId = result.productId,
                storeId = result.storeId,
                name = result.name,
                isHidden = result.isHidden,
                hiddenAt = result.hiddenAt,
            )
    }
}

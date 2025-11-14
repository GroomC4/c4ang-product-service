package com.groom.product.presentation.web.dto

import com.groom.product.application.dto.DeleteProductResult
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

/**
 * 상품 삭제 응답 DTO.
 */
@Schema(description = "상품 삭제 응답")
data class DeleteProductResponse(
    @Schema(description = "상품 ID", example = "123e4567-e89b-12d3-a456-426614174002")
    val productId: String,
    @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val storeId: String,
    @Schema(description = "상품명", example = "무선 마우스")
    val name: String,
    @Schema(description = "삭제일시", example = "2025-01-15T12:30:00")
    val deletedAt: LocalDateTime,
) {
    companion object {
        fun from(result: DeleteProductResult): DeleteProductResponse =
            DeleteProductResponse(
                productId = result.productId,
                storeId = result.storeId,
                name = result.name,
                deletedAt = result.deletedAt,
            )
    }
}

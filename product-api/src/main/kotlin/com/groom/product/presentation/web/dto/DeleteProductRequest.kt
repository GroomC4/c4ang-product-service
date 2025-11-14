package com.groom.product.presentation.web.dto

import com.groom.product.application.dto.DeleteProductCommand
import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

/**
 * 상품 삭제 요청 DTO.
 */
@Schema(description = "상품 삭제 요청")
data class DeleteProductRequest(
    @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    val storeId: String,
) {
    fun toCommand(productId: UUID): DeleteProductCommand =
        DeleteProductCommand(
            productId = productId,
            storeId = UUID.fromString(storeId),
        )
}

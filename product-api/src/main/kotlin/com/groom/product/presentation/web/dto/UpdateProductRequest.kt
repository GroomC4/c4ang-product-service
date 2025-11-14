package com.groom.product.presentation.web.dto

import com.groom.product.application.dto.UpdateProductCommand
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.util.UUID

/**
 * 상품 수정 요청 DTO.
 */
@Schema(description = "상품 수정 요청")
data class UpdateProductRequest(
    @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    val storeId: String,
    @Schema(description = "상품명", example = "무선 마우스", required = true)
    val name: String,
    @Schema(description = "가격", example = "29900", required = true)
    val price: BigDecimal,
    @Schema(description = "재고 수량", example = "100", required = true)
    val stockQuantity: Int,
    @Schema(description = "상품 설명", example = "편안한 그립감의 무선 마우스입니다.")
    val description: String?,
    @Schema(description = "썸네일 이미지 URL", example = "https://s3.amazonaws.com/bucket/thumbnail.jpg")
    val thumbnailUrl: String?,
    @Schema(description = "카테고리 ID", example = "123e4567-e89b-12d3-a456-426614174001", required = true)
    val categoryId: String,
) {
    fun toCommand(productId: UUID): UpdateProductCommand =
        UpdateProductCommand(
            productId = productId,
            storeId = UUID.fromString(storeId),
            name = name,
            price = price,
            stockQuantity = stockQuantity,
            description = description,
            thumbnailUrl = thumbnailUrl,
            categoryId = UUID.fromString(categoryId),
        )
}

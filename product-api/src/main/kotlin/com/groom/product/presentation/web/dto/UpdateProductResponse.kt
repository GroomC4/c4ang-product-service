package com.groom.product.presentation.web.dto

import com.groom.product.application.dto.UpdateProductResult
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 상품 수정 응답 DTO.
 */
@Schema(description = "상품 수정 응답")
data class UpdateProductResponse(
    @Schema(description = "상품 ID", example = "123e4567-e89b-12d3-a456-426614174002")
    val productId: String,
    @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000")
    val storeId: String,
    @Schema(description = "카테고리 ID", example = "123e4567-e89b-12d3-a456-426614174001")
    val categoryId: String,
    @Schema(description = "상품명", example = "무선 마우스")
    val name: String,
    @Schema(description = "상품 상태", example = "AVAILABLE")
    val status: String,
    @Schema(description = "가격", example = "29900")
    val price: BigDecimal,
    @Schema(description = "재고 수량", example = "100")
    val stockQuantity: Int,
    @Schema(description = "썸네일 이미지 URL", example = "https://s3.amazonaws.com/bucket/thumbnail.jpg")
    val thumbnailUrl: String?,
    @Schema(description = "상품 설명", example = "편안한 그립감의 무선 마우스입니다.")
    val description: String?,
    @Schema(description = "수정일시", example = "2025-01-15T11:30:00")
    val updatedAt: LocalDateTime,
) {
    companion object {
        fun from(result: UpdateProductResult): UpdateProductResponse =
            UpdateProductResponse(
                productId = result.productId,
                storeId = result.storeId,
                categoryId = result.categoryId,
                name = result.name,
                status = result.status,
                price = result.price,
                stockQuantity = result.stockQuantity,
                thumbnailUrl = result.thumbnailUrl,
                description = result.description,
                updatedAt = result.updatedAt,
            )
    }
}

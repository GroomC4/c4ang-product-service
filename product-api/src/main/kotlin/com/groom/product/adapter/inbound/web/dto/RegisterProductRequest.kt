package com.groom.product.adapter.`in`.web.dto

import com.groom.product.application.dto.RegisterProductCommand
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.util.UUID

/**
 * 상품 등록 REST API Request DTO.
 */
@Schema(description = "상품 등록 요청")
data class RegisterProductRequest(
    @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000", required = true)
    val storeId: String,
    @Schema(description = "카테고리 ID", example = "123e4567-e89b-12d3-a456-426614174001", required = true)
    val categoryId: String,
    @Schema(description = "상품명", example = "무선 마우스", required = true)
    val name: String,
    @Schema(description = "가격", example = "29900", required = true)
    val price: BigDecimal,
    @Schema(description = "재고 수량", example = "100", required = true)
    val stockQuantity: Int,
    @Schema(description = "썸네일 이미지 URL", example = "https://s3.amazonaws.com/bucket/thumbnail.jpg")
    val thumbnailUrl: String?,
    @Schema(description = "상품 설명", example = "편안한 그립감의 무선 마우스입니다.")
    val description: String?,
    @Schema(description = "상품 이미지 목록")
    val images: List<ProductImageRequest> = emptyList(),
    @Schema(description = "AI 설명 자동 생성 여부", example = "false")
    val useAiDescription: Boolean = false,
) {
    @Schema(description = "상품 이미지 정보")
    data class ProductImageRequest(
        @Schema(description = "이미지 타입 (PRIMARY, DETAIL)", example = "PRIMARY", required = true)
        val imageType: String,
        @Schema(description = "이미지 URL", example = "https://s3.amazonaws.com/bucket/image.jpg", required = true)
        val imageUrl: String,
        @Schema(description = "표시 순서", example = "1", required = true)
        val displayOrder: Int,
    )

    fun toCommand(): RegisterProductCommand =
        RegisterProductCommand(
            storeId = UUID.fromString(storeId),
            categoryId = UUID.fromString(categoryId),
            name = name,
            price = price,
            stockQuantity = stockQuantity,
            thumbnailUrl = thumbnailUrl,
            description = description,
            images =
                images.map { imageRequest ->
                    RegisterProductCommand.ProductImageDto(
                        imageType = imageRequest.imageType,
                        imageUrl = imageRequest.imageUrl,
                        displayOrder = imageRequest.displayOrder,
                    )
                },
            useAiDescription = useAiDescription,
        )
}

package com.groom.product.presentation.web.dto

import com.groom.product.application.dto.GetProductDetailResult
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 상품 상세 조회 Response DTO.
 */
@Schema(description = "상품 상세 조회 응답")
data class GetProductDetailResponse(
    @Schema(description = "상품 ID", example = "123e4567-e89b-12d3-a456-426614174002")
    val id: String,
    @Schema(description = "상품명", example = "무선 마우스")
    val name: String,
    @Schema(description = "상품 설명", example = "편안한 그립감의 무선 마우스입니다.")
    val description: String?,
    @Schema(description = "가격", example = "29900")
    val price: BigDecimal,
    @Schema(description = "재고 수량", example = "100")
    val stockQuantity: Int,
    @Schema(description = "상품 상태", example = "AVAILABLE")
    val status: String,
    @Schema(description = "카테고리 ID", example = "123e4567-e89b-12d3-a456-426614174001")
    val categoryId: String,
    @Schema(description = "카테고리 경로", example = "전자제품 > 컴퓨터 > 마우스")
    val categoryPath: String?,
    @Schema(description = "스토어 정보")
    val store: StoreInfo,
    @Schema(description = "상품 이미지 목록")
    val images: List<ProductImageInfo>,
    @Schema(description = "평균 평점", example = "4.5")
    val rating: BigDecimal?,
    @Schema(description = "리뷰 개수", example = "128")
    val reviewCount: Int,
    @Schema(description = "생성일시", example = "2025-01-15T10:30:00")
    val createdAt: String,
) {
    @Schema(description = "스토어 정보")
    data class StoreInfo(
        @Schema(description = "스토어 ID", example = "123e4567-e89b-12d3-a456-426614174000")
        val id: String,
        @Schema(description = "스토어명", example = "전자제품 스토어")
        val name: String,
    )

    @Schema(description = "상품 이미지 정보")
    data class ProductImageInfo(
        @Schema(description = "이미지 URL", example = "https://s3.amazonaws.com/bucket/image.jpg")
        val imageUrl: String,
        @Schema(description = "이미지 타입", example = "PRIMARY")
        val imageType: String,
    )

    companion object {
        fun from(result: GetProductDetailResult): GetProductDetailResponse =
            GetProductDetailResponse(
                id = result.id,
                name = result.name,
                description = result.description,
                price = result.price,
                stockQuantity = result.stockQuantity,
                status = result.status,
                categoryId = result.categoryId,
                categoryPath = result.categoryPath,
                store =
                    StoreInfo(
                        id = result.store.id,
                        name = result.store.name,
                    ),
                images =
                    result.images.map { image ->
                        ProductImageInfo(
                            imageUrl = image.imageUrl,
                            imageType = image.imageType,
                        )
                    },
                rating = result.rating,
                reviewCount = result.reviewCount,
                createdAt = result.createdAt,
            )
    }
}

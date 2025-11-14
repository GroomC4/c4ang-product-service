package com.groom.product.presentation.web.dto

import com.groom.product.application.dto.SearchProductsResult
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal

/**
 * 상품 목록 검색 Response DTO.
 *
 * @property totalElements 전체 상품 개수
 * @property totalPages 전체 페이지 수
 * @property currentPage 현재 페이지 번호
 * @property pageSize 페이지 크기
 * @property products 상품 목록
 */
@Schema(description = "상품 검색 응답")
data class SearchProductsResponse(
    @Schema(description = "전체 상품 개수", example = "100")
    val totalElements: Long,
    @Schema(description = "전체 페이지 수", example = "10")
    val totalPages: Int,
    @Schema(description = "현재 페이지 번호", example = "0")
    val currentPage: Int,
    @Schema(description = "페이지 크기", example = "10")
    val pageSize: Int,
    @Schema(description = "상품 목록")
    val products: List<ProductSummaryResponse>,
) {
    /**
     * 상품 요약 정보 Response DTO.
     */
    @Schema(description = "상품 요약 정보")
    data class ProductSummaryResponse(
        @Schema(description = "상품 ID", example = "123e4567-e89b-12d3-a456-426614174002")
        val id: String,
        @Schema(description = "상품명", example = "무선 마우스")
        val name: String,
        @Schema(description = "가격", example = "29900")
        val price: BigDecimal,
        @Schema(description = "썸네일 이미지 URL", example = "https://s3.amazonaws.com/bucket/thumbnail.jpg")
        val thumbnailUrl: String?,
        @Schema(description = "스토어명", example = "전자제품 스토어")
        val storeName: String,
        @Schema(description = "평균 평점", example = "4.5")
        val averageRating: BigDecimal?,
        @Schema(description = "리뷰 개수", example = "128")
        val reviewCount: Int,
        @Schema(description = "카테고리 경로", example = "전자제품 > 컴퓨터 > 마우스")
        val categoryPath: String?,
    )

    companion object {
        fun from(result: SearchProductsResult): SearchProductsResponse =
            SearchProductsResponse(
                totalElements = result.totalElements,
                totalPages = result.totalPages,
                currentPage = result.currentPage,
                pageSize = result.pageSize,
                products =
                    result.products.map { summary ->
                        ProductSummaryResponse(
                            id = summary.id,
                            name = summary.name,
                            price = summary.price,
                            thumbnailUrl = summary.thumbnailUrl,
                            storeName = summary.storeName,
                            averageRating = summary.averageRating,
                            reviewCount = summary.reviewCount,
                            categoryPath = summary.categoryPath,
                        )
                    },
            )
    }
}

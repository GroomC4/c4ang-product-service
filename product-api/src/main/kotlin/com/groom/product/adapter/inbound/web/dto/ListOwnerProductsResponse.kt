package com.groom.product.adapter.inbound.web.dto

import com.groom.product.application.dto.ListOwnerProductsResult
import io.swagger.v3.oas.annotations.media.Schema
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 판매자 상품 목록 조회 응답 DTO.
 */
@Schema(description = "판매자 상품 목록 조회 응답")
data class ListOwnerProductsResponse(
    @Schema(description = "전체 상품 개수", example = "50")
    val totalElements: Long,
    @Schema(description = "전체 페이지 수", example = "5")
    val totalPages: Int,
    @Schema(description = "현재 페이지 번호", example = "0")
    val currentPage: Int,
    @Schema(description = "페이지 크기", example = "10")
    val pageSize: Int,
    @Schema(description = "상품 목록")
    val products: List<OwnerProductSummary>,
) {
    @Schema(description = "판매자 상품 요약 정보")
    data class OwnerProductSummary(
        @Schema(description = "상품 ID", example = "123e4567-e89b-12d3-a456-426614174002")
        val productId: String,
        @Schema(description = "상품명", example = "무선 마우스")
        val name: String,
        @Schema(description = "가격", example = "29900")
        val price: BigDecimal,
        @Schema(description = "재고 수량", example = "100")
        val stockQuantity: Int,
        @Schema(description = "상품 상태", example = "AVAILABLE")
        val status: String,
        @Schema(description = "숨김 여부", example = "false")
        val isHidden: Boolean,
        @Schema(description = "삭제 여부", example = "false")
        val isDeleted: Boolean,
        @Schema(description = "썸네일 이미지 URL", example = "https://s3.amazonaws.com/bucket/thumbnail.jpg")
        val thumbnailUrl: String?,
        @Schema(description = "카테고리 ID", example = "123e4567-e89b-12d3-a456-426614174001")
        val categoryId: String?,
        @Schema(description = "생성일시", example = "2025-01-15T10:30:00")
        val createdAt: LocalDateTime?,
        @Schema(description = "수정일시", example = "2025-01-15T11:30:00")
        val updatedAt: LocalDateTime?,
    )

    companion object {
        fun from(result: ListOwnerProductsResult): ListOwnerProductsResponse =
            ListOwnerProductsResponse(
                totalElements = result.totalElements,
                totalPages = result.totalPages,
                currentPage = result.currentPage,
                pageSize = result.pageSize,
                products =
                    result.products.map { product ->
                        OwnerProductSummary(
                            productId = product.productId,
                            name = product.name,
                            price = product.price,
                            stockQuantity = product.stockQuantity,
                            status = product.status,
                            isHidden = product.isHidden,
                            isDeleted = product.isDeleted,
                            thumbnailUrl = product.thumbnailUrl,
                            categoryId = product.categoryId,
                            createdAt = product.createdAt,
                            updatedAt = product.updatedAt,
                        )
                    },
            )
    }
}

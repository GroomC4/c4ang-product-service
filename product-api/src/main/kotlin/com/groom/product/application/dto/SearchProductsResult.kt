package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import java.math.BigDecimal

/**
 * 상품 목록 검색 Result DTO.
 *
 * @property totalElements 전체 상품 개수
 * @property totalPages 전체 페이지 수
 * @property currentPage 현재 페이지 번호
 * @property pageSize 페이지 크기
 * @property products 상품 목록
 */
data class SearchProductsResult(
    val totalElements: Long,
    val totalPages: Int,
    val currentPage: Int,
    val pageSize: Int,
    val products: List<ProductSummary>,
) {
    /**
     * 상품 요약 정보.
     *
     * @property id 상품 ID
     * @property name 상품명
     * @property price 가격
     * @property thumbnailUrl 썸네일 URL
     * @property storeName 스토어명
     * @property averageRating 평균 평점 (향후 구현)
     * @property reviewCount 리뷰 개수 (향후 구현)
     * @property categoryPath 카테고리 경로 (예: "전자제품 > 컴퓨터 주변기기 > 마우스")
     */
    data class ProductSummary(
        val id: String,
        val name: String,
        val price: BigDecimal,
        val thumbnailUrl: String?,
        val storeName: String,
        val averageRating: BigDecimal?,
        val reviewCount: Int,
        val categoryPath: String?,
    ) {
        companion object {
            fun from(
                product: Product,
                categoryPath: String?,
            ): ProductSummary =
                ProductSummary(
                    id = product.id.toString(),
                    name = product.name,
                    price = product.price,
                    thumbnailUrl = product.thumbnailUrl,
                    storeName = product.storeName, // 비정규화 컬럼 직접 사용
                    averageRating = null, // TODO: 리뷰 기능 구현 시 추가
                    reviewCount = 0, // TODO: 리뷰 기능 구현 시 추가
                    categoryPath = categoryPath,
                )
        }
    }
}

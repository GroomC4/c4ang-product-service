package com.groom.product.application.dto

import java.math.BigDecimal

/**
 * 상품 목록 검색 Query DTO.
 *
 * @property productName 상품명 검색 키워드 (부분 일치 또는 유사도 검색)
 * @property storeName 스토어명 검색 키워드 (부분 일치 또는 유사도 검색)
 * @property minPrice 최소 가격
 * @property maxPrice 최대 가격
 * @property sortBy 정렬 기준 (price, created_at, rating, similarity)
 * @property sortOrder 정렬 방향 (asc, desc)
 * @property page 페이지 번호 (0부터 시작)
 * @property size 페이지 크기
 * @property useSimilaritySearch 유사도 검색 사용 여부 (true: pg_trgm 유사도 검색, false: LIKE 부분 일치)
 */
data class SearchProductsQuery(
    val productName: String? = null,
    val storeName: String? = null,
    val minPrice: BigDecimal? = null,
    val maxPrice: BigDecimal? = null,
    val sortBy: String = "created_at",
    val sortOrder: String = "desc",
    val page: Int = 0,
    val size: Int = 10,
    val useSimilaritySearch: Boolean = false,
)

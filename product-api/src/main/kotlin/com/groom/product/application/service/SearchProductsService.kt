package com.groom.product.application.service

import com.groom.product.application.dto.SearchProductsQuery
import com.groom.product.application.dto.SearchProductsResult
import com.groom.product.domain.port.SearchProductsPort
import com.groom.product.adapter.outbound.persistence.ProductSpecifications
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 상품 목록 검색 서비스.
 *
 * 다양한 조건으로 상품을 검색하고 페이징 처리된 결과를 반환합니다.
 * - 상품명 검색 (부분 일치 또는 유사도 검색)
 * - 스토어명 검색 (부분 일치 또는 유사도 검색)
 * - 가격 범위 검색
 * - 정렬 (가격, 생성일시, 평점)
 * - 페이지네이션
 *
 * **유사도 검색:**
 * - PostgreSQL의 pg_trgm 확장을 사용하여 텍스트 유사도 기반 검색 지원
 * - `useSimilaritySearch=true` 파라미터로 활성화
 *
 * **N+1 문제 해결:**
 * - 카테고리 경로를 배치로 조회하여 N+1 문제 방지
 * - 각 카테고리는 Recursive CTE로 최적화된 쿼리 실행
 *
 * **DDD 패턴 적용:**
 * - ProductSearcher Port를 통한 의존성 역전
 * - Infrastructure Layer 구현체가 아닌 Domain Layer Port에 의존
 */
@Service
class SearchProductsService(
    private val searchProductsPort: SearchProductsPort,
    private val categoryPathService: CategoryPathService,
) {
    /**
     * 조건에 맞는 상품 목록을 검색합니다.
     *
     * @param query 검색 조건
     * @return 페이징된 상품 목록
     */
    @Transactional(readOnly = true)
    fun search(query: SearchProductsQuery): SearchProductsResult {
        // 1. Specification 조합 (storeName은 비정규화 컬럼으로 직접 검색)
        val spec =
            if (query.useSimilaritySearch) {
                // 유사도 검색 모드
                ProductSpecifications.allOf(
                    ProductSpecifications.notHidden(), // 숨김 처리되지 않은 상품만
                    ProductSpecifications.nameSimilarTo(query.productName),
                    ProductSpecifications.storeNameSimilarTo(query.storeName),
                    ProductSpecifications.priceGreaterThanOrEqual(query.minPrice),
                    ProductSpecifications.priceLessThanOrEqual(query.maxPrice),
                )
            } else {
                // 일반 부분 일치 검색 모드
                ProductSpecifications.allOf(
                    ProductSpecifications.notHidden(), // 숨김 처리되지 않은 상품만
                    ProductSpecifications.nameContains(query.productName),
                    ProductSpecifications.storeNameContains(query.storeName),
                    ProductSpecifications.priceGreaterThanOrEqual(query.minPrice),
                    ProductSpecifications.priceLessThanOrEqual(query.maxPrice),
                )
            }

        // 2. Pageable 생성
        val sort = createSort(query.sortBy, query.sortOrder)
        val pageable = PageRequest.of(query.page, query.size, sort)

        // 3. 검색 실행
        val productPage = searchProductsPort.search(spec, pageable)

        // 4. N+1 방지: 모든 카테고리 ID를 수집하여 배치 조회
        val categoryIds = productPage.content.map { product -> product.categoryId }
        val categoryPathMap = categoryPathService.buildCategoryPathsBatch(categoryIds)

        // 5. Result DTO 생성 (비정규화 컬럼 활용으로 Store 조회 불필요)
        val productSummaries =
            productPage.content.map { product ->
                val categoryPath = categoryPathMap[product.categoryId]
                SearchProductsResult.ProductSummary.from(product, categoryPath)
            }

        return SearchProductsResult(
            totalElements = productPage.totalElements,
            totalPages = productPage.totalPages,
            currentPage = productPage.number,
            pageSize = productPage.size,
            products = productSummaries,
        )
    }

    /**
     * 정렬 기준과 방향으로 Sort 객체 생성.
     */
    private fun createSort(
        sortBy: String,
        sortOrder: String,
    ): Sort {
        val direction =
            if (sortOrder.equals("asc", ignoreCase = true)) {
                Sort.Direction.ASC
            } else {
                Sort.Direction.DESC
            }

        val property =
            when (sortBy.lowercase()) {
                "price" -> "price"
                "rating" -> "averageRating" // TODO: 리뷰 기능 구현 시 활성화
                else -> "createdAt"
            }

        return Sort.by(direction, property)
    }
}

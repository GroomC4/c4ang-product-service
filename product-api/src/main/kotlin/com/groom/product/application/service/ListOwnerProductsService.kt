package com.groom.product.application.service

import com.groom.product.adapter.outbound.persistence.ProductSpecifications
import com.groom.product.application.dto.ListOwnerProductsQuery
import com.groom.product.application.dto.ListOwnerProductsResult
import com.groom.product.domain.port.SearchProductsPort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 판매자 상품 목록 조회 서비스.
 *
 * 판매자가 자신의 모든 상품을 조회할 수 있습니다.
 * - 숨김 처리된 상품 포함
 * - 삭제된 상품은 선택적으로 포함 (includeDeleted 파라미터)
 * - 페이지네이션 및 정렬 지원
 */
@Service
class ListOwnerProductsService(
    private val searchProductsPort: SearchProductsPort,
) {
    /**
     * 판매자의 상품 목록을 조회합니다.
     *
     * @param query 조회 조건
     * @return 페이징된 상품 목록
     */
    @Transactional(readOnly = true)
    fun list(query: ListOwnerProductsQuery): ListOwnerProductsResult {
        // 1. Specification 조합
        val spec =
            if (query.includeDeleted) {
                // 삭제된 상품 포함: 스토어 ID만 체크
                ProductSpecifications.allOf(
                    ProductSpecifications.belongsToStore(query.storeId),
                )
            } else {
                // 삭제된 상품 제외: 스토어 ID + notDeleted
                ProductSpecifications.allOf(
                    ProductSpecifications.belongsToStore(query.storeId),
                    ProductSpecifications.notDeleted(),
                )
            }

        // 2. Pageable 생성
        val sort = createSort(query.sortBy, query.sortOrder)
        val pageable = PageRequest.of(query.page, query.size, sort)

        // 3. 검색 실행
        val productPage = searchProductsPort.search(spec, pageable)

        // 4. Result DTO 생성
        val productSummaries =
            productPage.content.map { product ->
                ListOwnerProductsResult.OwnerProductSummary.from(product)
            }

        return ListOwnerProductsResult(
            totalElements = productPage.totalElements,
            totalPages = productPage.totalPages,
            currentPage = productPage.number,
            pageSize = productPage.size,
            products = productSummaries,
        )
    }

    private fun createSort(
        sortBy: String,
        sortOrder: String,
    ): Sort {
        val direction = if (sortOrder.equals("desc", ignoreCase = true)) Sort.Direction.DESC else Sort.Direction.ASC

        return when (sortBy.lowercase()) {
            "name" -> Sort.by(direction, "name")
            "price" -> Sort.by(direction, "price")
            "stock" -> Sort.by(direction, "stockQuantity")
            else -> Sort.by(direction, "createdAt") // 기본값: 생성일시
        }
    }
}

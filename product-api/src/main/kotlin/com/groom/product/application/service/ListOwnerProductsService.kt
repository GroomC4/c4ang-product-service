package com.groom.product.application.service

import com.groom.product.application.dto.ListOwnerProductsQuery
import com.groom.product.application.dto.ListOwnerProductsResult
import com.groom.product.infrastructure.repository.ProductPersistenceAdapter
import com.groom.product.infrastructure.repository.ProductSpecifications
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
    private val productRepository: ProductPersistenceAdapter,
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
        val productPage = productRepository.findAll(spec, pageable)

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
                "name" -> "name"
                "price" -> "price"
                "stockquantity" -> "stockQuantity"
                "createdat" -> "createdAt"
                else -> "createdAt"
            }

        return Sort.by(direction, property)
    }
}

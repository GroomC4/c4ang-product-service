package com.groom.product.application.service

import com.groom.product.common.exception.ProductException
import com.groom.product.application.dto.GetProductDetailQuery
import com.groom.product.application.dto.GetProductDetailResult
import com.groom.product.domain.port.LoadProductPort
import com.groom.ecommerce.store.domain.port.StoreReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 상품 상세 조회 서비스.
 *
 * 상품 ID로 상품 상세 정보를 조회합니다.
 * - 상품 기본 정보
 * - 스토어 정보
 * - 카테고리 정보 (카테고리 경로 포함)
 * - 상품 이미지 목록
 *
 * **N+1 문제 해결:**
 * - Product 조회 시 Images를 Fetch Join으로 함께 조회 (1번 쿼리)
 * - Store 조회 (1번 쿼리)
 * - Category 경로 조회 시 Recursive CTE로 한 번에 조회 (1번 쿼리)
 * - **총 3번의 쿼리로 최적화** (기존 6번 이상 → 3번)
 *
 * **DDD 패턴 적용:**
 * - Port-Adapter 패턴 적용 (ProductReader, StoreReader 의존)
 * - Infrastructure Layer 구현체가 아닌 Domain Layer Port에 의존
 */
@Service
class GetProductDetailService(
    private val productReader: ProductReader,
    private val storeReader: StoreReader,
    private val categoryPathService: CategoryPathService,
) {
    /**
     * 상품 상세 정보를 조회합니다.
     *
     * N+1 문제를 방지하기 위해:
     * - `findByIdWithImages()`로 상품과 이미지를 한 번에 조회
     * - CategoryPathService가 Recursive CTE로 카테고리 경로를 한 번에 조회
     *
     * @param query 조회할 상품 ID
     * @return 상품 상세 정보
     * @throws ProductException.ProductNotFound 상품을 찾을 수 없는 경우
     */
    @Transactional(readOnly = true)
    fun getProductDetail(query: GetProductDetailQuery): GetProductDetailResult {
        // 1. 상품 조회 (이미지 포함 - Fetch Join)
        val product =
            productReader
                .findByIdWithImages(query.productId)
                .orElseThrow { ProductException.ProductNotFound(query.productId) }

        // 2. 스토어 조회
        val store =
            storeReader
                .findById(product.storeId)
                .orElseThrow { IllegalStateException("Store not found: ${product.storeId}") }

        // 3. 카테고리 경로 생성 (Recursive CTE 사용)
        val categoryPath = categoryPathService.buildCategoryPath(product.categoryId)

        // 4. Result DTO 생성
        return GetProductDetailResult.from(
            product = product,
            store = store,
            categoryPath = categoryPath,
        )
    }
}

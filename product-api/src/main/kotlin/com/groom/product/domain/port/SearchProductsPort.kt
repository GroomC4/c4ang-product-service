package com.groom.product.domain.port

import com.groom.product.domain.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

/**
 * Product 검색을 위한 Outbound Port.
 *
 * Domain이 외부 인프라(검색 엔진 또는 데이터베이스)에 요구하는 계약입니다.
 *
 * **Specification 기반 동적 쿼리 지원:**
 * - JPA Specification을 사용한 동적 검색 조건 조합
 * - 유사도 검색, 가격 범위 검색 등 복잡한 조건 지원
 *
 * **LoadProductPort와의 관계:**
 * - LoadProductPort: 단순 조회 (ID 기반)
 * - SearchProductsPort: 복잡한 검색 (조건 기반, 페이징)
 *
 * Note: 현재는 JPA Specification을 사용하지만, 향후 Elasticsearch 등으로 전환 가능
 */
interface SearchProductsPort {
    /**
     * Specification과 Pageable을 사용하여 상품 목록을 검색합니다.
     *
     * @param spec 검색 조건 Specification
     * @param pageable 페이지네이션 정보
     * @return 페이징된 상품 목록
     */
    fun search(
        spec: Specification<Product>,
        pageable: Pageable,
    ): Page<Product>
}

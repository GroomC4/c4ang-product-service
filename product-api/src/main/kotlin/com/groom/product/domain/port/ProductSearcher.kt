package com.groom.product.domain.port

import com.groom.product.domain.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification

/**
 * Product 검색을 위한 Port 인터페이스.
 *
 * Domain Layer가 Infrastructure Layer에 의존하지 않도록
 * 의존성을 역전시키는 Port-Adapter 패턴의 Port 역할을 합니다.
 *
 * **Specification 기반 동적 쿼리 지원:**
 * - JPA Specification을 사용한 동적 검색 조건 조합
 * - 유사도 검색, 가격 범위 검색 등 복잡한 조건 지원
 *
 * **ProductReader와의 관계:**
 * - ProductReader: 단순 조회 (ID 기반)
 * - ProductSearcher: 복잡한 검색 (조건 기반, 페이징)
 */
interface ProductSearcher {
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

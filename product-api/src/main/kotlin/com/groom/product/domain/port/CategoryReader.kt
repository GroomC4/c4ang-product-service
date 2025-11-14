package com.groom.product.domain.port

import com.groom.product.domain.model.ProductCategory
import java.util.Optional
import java.util.UUID

/**
 * 카테고리 조회를 위한 Port 인터페이스.
 *
 * Domain Layer가 Infrastructure Layer에 의존하지 않도록
 * 의존성을 역전시키는 Port-Adapter 패턴의 Port 역할을 합니다.
 */
interface CategoryReader {
    /**
     * 카테고리 ID로 카테고리를 조회합니다.
     *
     * @param categoryId 카테고리 ID
     * @return 카테고리 Optional
     */
    fun findById(categoryId: UUID): Optional<ProductCategory>

    /**
     * 카테고리 ID로부터 루트까지의 모든 조상 카테고리를 조회합니다.
     * N+1 문제를 방지하기 위해 한 번의 쿼리로 모든 조상을 조회합니다.
     *
     * @param categoryId 카테고리 ID
     * @return 루트부터 현재 카테고리까지의 계층 구조 리스트 (순서: 루트 -> 리프)
     */
    fun findAncestorCategoriesById(categoryId: UUID): List<ProductCategory>
}

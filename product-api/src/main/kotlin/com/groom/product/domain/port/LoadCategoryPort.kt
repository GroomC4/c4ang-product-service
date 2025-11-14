package com.groom.product.domain.port

import com.groom.product.domain.model.ProductCategory
import java.util.UUID

/**
 * 카테고리 조회를 위한 Outbound Port.
 *
 * Domain이 외부 인프라(영속성 계층)에 요구하는 계약입니다.
 */
interface LoadCategoryPort {
    /**
     * ID로 카테고리를 조회합니다.
     *
     * @param id 카테고리 ID
     * @return 카테고리 또는 null (존재하지 않을 경우)
     */
    fun loadById(id: UUID): ProductCategory?

    /**
     * 카테고리 ID로부터 루트까지의 모든 조상 카테고리를 조회합니다.
     * N+1 문제를 방지하기 위해 한 번의 쿼리로 모든 조상을 조회합니다.
     *
     * @param id 카테고리 ID
     * @return 루트부터 현재 카테고리까지의 계층 구조 리스트 (순서: 루트 -> 리프)
     */
    fun loadAncestorCategoriesById(id: UUID): List<ProductCategory>
}

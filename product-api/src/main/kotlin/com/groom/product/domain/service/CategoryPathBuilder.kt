package com.groom.product.domain.service

import com.groom.product.domain.model.ProductCategory
import com.groom.product.domain.port.LoadCategoryPort
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * 카테고리 경로 생성 도메인 서비스.
 *
 * 카테고리 계층 구조를 탐색하여 루트부터 현재 카테고리까지의
 * 경로를 생성하는 순수한 비즈니스 로직을 담당합니다.
 *
 * **N+1 문제 해결:**
 * - `findAncestorCategoriesById()`를 사용하여 한 번의 쿼리로 모든 조상 카테고리 조회
 * - Recursive CTE를 활용한 효율적인 계층 구조 조회
 *
 * 캐싱은 Application Layer(CategoryPathService)에서 처리하며,
 * 이 클래스는 순수한 비즈니스 로직만 담당합니다.
 *
 * @property categoryReader 카테고리 조회 Port (의존성 역전)
 */
@Component
class CategoryPathBuilder(
    private val categoryReader: CategoryReader,
) {
    companion object {
        private const val PATH_SEPARATOR = " > "
    }

    /**
     * 카테고리 ID로부터 전체 경로를 생성합니다.
     *
     * N+1 문제를 방지하기 위해 `findAncestorCategoriesById()`를 사용하여
     * 한 번의 쿼리로 모든 조상 카테고리를 조회합니다.
     *
     * @param categoryId 카테고리 ID
     * @return 카테고리 경로 문자열 (예: "전자제품 > 컴퓨터 주변기기 > 마우스")
     */
    fun buildPath(categoryId: UUID): String {
        val hierarchy = buildCategoryHierarchy(categoryId)
        return hierarchy.joinToString(PATH_SEPARATOR) { it.name }
    }

    /**
     * 카테고리 계층 구조를 루트부터 현재 카테고리까지 리스트로 반환합니다.
     *
     * N+1 문제를 방지하기 위해 `findAncestorCategoriesById()`를 사용하여
     * 한 번의 쿼리로 모든 조상 카테고리를 조회합니다.
     * (Recursive CTE 활용)
     *
     * @param categoryId 카테고리 ID
     * @return 루트부터 현재 카테고리까지의 리스트 (순서: 루트 -> 리프)
     */
    fun buildCategoryHierarchy(categoryId: UUID): List<ProductCategory> {
        // N+1 문제 해결: 한 번의 쿼리로 모든 조상 카테고리 조회
        return categoryReader.findAncestorCategoriesById(categoryId)
    }
}

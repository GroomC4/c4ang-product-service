package com.groom.product.application.service

import com.groom.product.domain.service.CategoryPathBuilder
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * 카테고리 경로 조회 애플리케이션 서비스.
 *
 * 카테고리 경로를 생성하는 유스케이스를 담당합니다.
 * 도메인 서비스(CategoryPathBuilder)를 활용하여 비즈니스 로직을 수행하며,
 * 향후 캐싱 등 기술적 관심사를 이 계층에서 처리할 수 있습니다.
 *
 * **N+1 문제 해결:**
 * - `buildCategoryPathsBatch()` 메서드로 여러 카테고리의 경로를 한 번에 조회
 * - 내부적으로 Recursive CTE를 활용하여 최적화
 *
 * **향후 캐싱 적용 예시:**
 * ```kotlin
 * @Cacheable(value = ["categoryPath"], key = "#categoryId")
 * fun buildCategoryPath(categoryId: UUID): String {
 *     return categoryPathBuilder.buildPath(categoryId)
 * }
 * ```
 *
 * @property categoryPathBuilder 카테고리 경로 생성 도메인 서비스
 */
@Service
class CategoryPathService(
    private val categoryPathBuilder: CategoryPathBuilder,
) {
    /**
     * 카테고리 ID로부터 전체 경로를 생성합니다.
     *
     * @param categoryId 카테고리 ID
     * @return 카테고리 경로 문자열 (예: "전자제품 > 컴퓨터 주변기기 > 마우스")
     */
    fun buildCategoryPath(categoryId: UUID): String = categoryPathBuilder.buildPath(categoryId)

    /**
     * 여러 카테고리 ID로부터 경로를 일괄 생성합니다 (N+1 방지).
     *
     * 각 카테고리마다 Recursive CTE를 사용하여 효율적으로 조회합니다.
     * 캐싱을 적용하면 더욱 효율적으로 동작합니다.
     *
     * @param categoryIds 카테고리 ID 목록
     * @return 카테고리 ID를 키로 하는 경로 맵 (예: {uuid1 -> "전자제품 > 마우스", uuid2 -> "의류 > 상의"})
     */
    fun buildCategoryPathsBatch(categoryIds: List<UUID>): Map<UUID, String> =
        categoryIds
            .distinct()
            .associateWith { categoryId ->
                categoryPathBuilder.buildPath(categoryId)
            }
}

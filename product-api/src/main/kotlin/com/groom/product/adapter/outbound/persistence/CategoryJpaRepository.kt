package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

/**
 * ProductCategory JPA Repository (Adapter 내부용).
 *
 * 이 인터페이스는 Persistence Adapter 내부에서만 사용됩니다.
 */
interface CategoryJpaRepository : JpaRepository<ProductCategory, UUID> {
    /**
     * 카테고리 ID로부터 루트까지의 모든 조상 카테고리를 조회합니다.
     * N+1 문제를 방지하기 위해 Recursive CTE를 사용합니다.
     */
    @Query(
        value =
            """
        WITH RECURSIVE category_path AS (
            SELECT id, name, parent_category_id, depth, created_at, updated_at, deleted_at, 0 AS path_index
            FROM p_product_category
            WHERE id = :categoryId

            UNION ALL

            SELECT c.id, c.name, c.parent_category_id, c.depth, c.created_at, c.updated_at, c.deleted_at, cp.path_index + 1
            FROM p_product_category c
            INNER JOIN category_path cp ON c.id = cp.parent_category_id
        )
        SELECT id, name, parent_category_id, depth, created_at, updated_at, deleted_at FROM category_path
        ORDER BY depth ASC
        """,
        nativeQuery = true,
    )
    fun findAncestorCategoriesById(
        @Param("categoryId") categoryId: UUID,
    ): List<ProductCategory>
}

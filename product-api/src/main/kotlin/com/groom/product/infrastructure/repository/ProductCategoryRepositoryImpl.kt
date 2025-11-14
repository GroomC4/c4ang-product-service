package com.groom.product.infrastructure.repository

import com.groom.product.domain.model.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface ProductCategoryRepositoryImpl : JpaRepository<ProductCategory, UUID> {
    fun findByName(name: String): Optional<ProductCategory>

    fun findByParentCategoryId(parentCategoryId: UUID): List<ProductCategory>

    fun findByParentCategoryIdIsNull(): List<ProductCategory> // 루트 카테고리

    fun findByDepth(depth: Int): List<ProductCategory>

    /**
     * 여러 Category를 한 번에 조회 (N+1 방지용).
     */
    fun findByIdIn(ids: Collection<UUID>): List<ProductCategory>

    /**
     * 카테고리 ID로부터 루트까지의 모든 조상 카테고리를 조회합니다.
     * Recursive CTE를 사용하여 N+1 문제를 방지합니다.
     *
     * PostgreSQL의 Recursive CTE를 활용하여 한 번의 쿼리로
     * 현재 카테고리부터 루트까지의 모든 조상을 조회합니다.
     *
     * @param categoryId 카테고리 ID
     * @return 루트부터 현재 카테고리까지의 계층 구조 리스트 (순서: 루트 -> 리프)
     */
    @Query(
        nativeQuery = true,
        value =
            """
            WITH RECURSIVE category_path AS (
                -- 기준 카테고리 (리프)
                SELECT id, name, parent_category_id, depth, created_at, updated_at
                FROM p_product_category
                WHERE id = :categoryId

                UNION ALL

                -- 부모 카테고리 재귀 조회
                SELECT parent.id, parent.name, parent.parent_category_id, parent.depth, parent.created_at, parent.updated_at
                FROM p_product_category parent
                INNER JOIN category_path child ON parent.id = child.parent_category_id
            )
            SELECT id, name, parent_category_id, depth, NULL as deleted_at, created_at, updated_at
            FROM category_path
            ORDER BY depth ASC
            """,
    )
    fun findAncestorCategoriesById(
        @Param("categoryId") categoryId: UUID,
    ): List<ProductCategory>
}

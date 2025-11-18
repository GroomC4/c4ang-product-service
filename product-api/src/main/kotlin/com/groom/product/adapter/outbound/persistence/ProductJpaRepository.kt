package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional
import java.util.UUID

/**
 * Product JPA Repository (Adapter 내부용).
 *
 * 이 인터페이스는 Persistence Adapter 내부에서만 사용됩니다.
 * Domain Layer는 이 인터페이스를 직접 의존하지 않고, Port를 통해 접근합니다.
 */
interface ProductJpaRepository :
    JpaRepository<Product, UUID>,
    JpaSpecificationExecutor<Product> {
    fun findByStoreId(storeId: UUID): List<Product>

    fun findByCategoryId(categoryId: UUID): List<Product>

    fun findByStatus(status: String): List<Product>

    fun findByStoreIdAndStatus(
        storeId: UUID,
        status: String,
    ): List<Product>

    fun findByNameContaining(name: String): List<Product>

    fun existsByName(name: String): Boolean

    /**
     * 상품 ID로 상품을 조회합니다 (이미지 컬렉션 Fetch Join).
     * N+1 문제를 방지하기 위해 이미지를 한 번의 쿼리로 함께 조회합니다.
     */
    @Query(
        """
        SELECT DISTINCT p
        FROM Product p
        LEFT JOIN FETCH p.images
        WHERE p.id = :productId
        """,
    )
    fun findByIdWithImages(
        @Param("productId") productId: UUID,
    ): Optional<Product>

    /**
     * 특정 스토어의 모든 상품의 비정규화 컬럼(store_name)을 일괄 업데이트합니다.
     */
    @Modifying
    @Query("UPDATE Product p SET p.storeName = :newStoreName WHERE p.storeId = :storeId")
    fun bulkUpdateStoreName(
        @Param("storeId") storeId: UUID,
        @Param("newStoreName") newStoreName: String,
    ): Int
}

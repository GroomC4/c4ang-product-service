package com.groom.product.infrastructure.repository

import com.groom.product.domain.model.Product
import com.groom.product.domain.port.LoadProductPort
import com.groom.product.domain.port.SearchProductsPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface ProductRepositoryImpl :
    JpaRepository<Product, UUID>,
    JpaSpecificationExecutor<Product>,
    ProductReader,
    ProductSearcher {
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
     *
     * @param productId 상품 ID
     * @return 상품 Optional (이미지 포함)
     */
    @Query(
        """
        SELECT DISTINCT p
        FROM Product p
        LEFT JOIN FETCH p.images
        WHERE p.id = :productId
        """,
    )
    override fun findByIdWithImages(
        @Param("productId") productId: UUID,
    ): Optional<Product>

    /**
     * ProductSearcher 구현: Specification과 Pageable을 사용하여 상품 검색.
     *
     * JpaSpecificationExecutor의 findAll을 ProductSearcher.search로 위임합니다.
     *
     * @param spec 검색 조건 Specification
     * @param pageable 페이지네이션 정보
     * @return 페이징된 상품 목록
     */
    override fun search(
        spec: Specification<Product>,
        pageable: Pageable,
    ): Page<Product> = findAll(spec, pageable)

    /**
     * 특정 스토어의 모든 상품의 비정규화 컬럼(store_name)을 일괄 업데이트합니다.
     * JPQL bulk update를 사용하여 효율적으로 처리합니다.
     *
     * @param storeId 스토어 ID
     * @param newStoreName 새로운 스토어 이름
     * @return 업데이트된 행 수
     */
    @Modifying
    @Query("UPDATE Product p SET p.storeName = :newStoreName WHERE p.storeId = :storeId")
    fun bulkUpdateStoreName(
        @Param("storeId") storeId: UUID,
        @Param("newStoreName") newStoreName: String,
    ): Int
}

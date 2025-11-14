package com.groom.product.domain.port

import com.groom.product.domain.model.Product
import java.util.Optional
import java.util.UUID

/**
 * Product 조회를 위한 Port 인터페이스.
 *
 * Domain Layer가 Infrastructure Layer에 의존하지 않도록
 * 의존성을 역전시키는 Port-Adapter 패턴의 Port 역할을 합니다.
 */
interface ProductReader {
    /**
     * 상품 ID로 상품을 조회합니다.
     *
     * @param productId 상품 ID
     * @return 상품 Optional
     */
    fun findById(productId: UUID): Optional<Product>

    /**
     * 상품 ID로 상품을 조회합니다 (이미지 컬렉션 Fetch Join).
     * N+1 문제를 방지하기 위해 이미지를 한 번의 쿼리로 함께 조회합니다.
     *
     * @param productId 상품 ID
     * @return 상품 Optional (이미지 포함)
     */
    fun findByIdWithImages(productId: UUID): Optional<Product>

    /**
     * 여러 상품 ID로 상품 목록을 조회합니다 (Batch).
     *
     * @param productIds 상품 ID 목록
     * @return 조회된 상품 목록
     */
    fun findAllById(productIds: Iterable<UUID>): List<Product>
}

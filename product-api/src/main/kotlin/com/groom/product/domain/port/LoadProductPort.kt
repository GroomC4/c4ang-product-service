package com.groom.product.domain.port

import com.groom.product.domain.model.Product
import java.util.UUID

/**
 * Product 조회를 위한 Outbound Port.
 *
 * Domain이 외부 인프라(영속성 계층)에 요구하는 계약입니다.
 * JPA와 같은 특정 기술에 의존하지 않는 순수한 비즈니스 인터페이스입니다.
 */
interface LoadProductPort {
    /**
     * ID로 상품을 조회합니다.
     *
     * @param id 상품 ID
     * @return 상품 또는 null (존재하지 않을 경우)
     */
    fun loadById(id: UUID): Product?

    /**
     * ID로 상품을 조회합니다 (이미지 컬렉션 포함).
     * N+1 문제를 방지하기 위해 이미지를 한 번의 쿼리로 함께 조회합니다.
     *
     * @param id 상품 ID
     * @return 상품 또는 null (존재하지 않을 경우, 이미지 포함)
     */
    fun loadByIdWithImages(id: UUID): Product?

    /**
     * 여러 상품 ID로 상품 목록을 조회합니다 (Batch).
     *
     * @param ids 상품 ID 목록
     * @return 조회된 상품 목록 (존재하는 상품만)
     */
    fun loadAllById(ids: Iterable<UUID>): List<Product>

    /**
     * 스토어 ID로 상품 목록을 조회합니다.
     *
     * @param storeId 스토어 ID
     * @return 해당 스토어의 상품 목록
     */
    fun loadByStoreId(storeId: UUID): List<Product>

    /**
     * 상품명 존재 여부를 확인합니다.
     *
     * @param name 상품명
     * @return 존재 여부
     */
    fun existsByName(name: String): Boolean
}

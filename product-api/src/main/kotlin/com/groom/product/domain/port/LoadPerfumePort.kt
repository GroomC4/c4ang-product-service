package com.groom.product.domain.port

import com.groom.product.domain.model.Perfume
import java.util.UUID

/**
 * Perfume 조회를 위한 Outbound Port.
 *
 * Domain이 외부 인프라(영속성 계층)에 요구하는 계약입니다.
 */
interface LoadPerfumePort {
    /**
     * ID로 향수를 조회합니다.
     *
     * @param id 향수 ID
     * @return 향수 또는 null (존재하지 않을 경우)
     */
    fun loadById(id: UUID): Perfume?

    /**
     * Product ID로 향수를 조회합니다.
     *
     * @param productId Product ID
     * @return 향수 또는 null (존재하지 않을 경우)
     */
    fun loadByProductId(productId: UUID): Perfume?

    /**
     * 여러 향수 ID로 향수 목록을 조회합니다.
     *
     * @param ids 향수 ID 목록
     * @return 조회된 향수 목록 (존재하는 향수만)
     */
    fun loadAllById(ids: Iterable<UUID>): List<Perfume>

    /**
     * 여러 Product ID로 향수 목록을 조회합니다.
     *
     * @param productIds Product ID 목록
     * @return 조회된 향수 목록 (존재하는 향수만)
     */
    fun loadAllByProductIds(productIds: Iterable<UUID>): List<Perfume>

    /**
     * 브랜드로 향수 목록을 조회합니다.
     *
     * @param brand 브랜드명
     * @return 해당 브랜드의 향수 목록
     */
    fun loadByBrand(brand: String): List<Perfume>

    /**
     * 성별로 향수 목록을 조회합니다.
     *
     * @param gender 성별 (Male, Female, Unisex)
     * @return 해당 성별의 향수 목록
     */
    fun loadByGender(gender: String): List<Perfume>

    /**
     * 삭제되지 않은 모든 향수를 조회합니다.
     *
     * @return 삭제되지 않은 향수 목록
     */
    fun loadAll(): List<Perfume>
}

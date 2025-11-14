package com.groom.product.domain.port

import com.groom.product.domain.model.Product

/**
 * Product 저장을 위한 Outbound Port.
 *
 * Domain이 외부 인프라(영속성 계층)에 요구하는 계약입니다.
 */
interface SaveProductPort {
    /**
     * 상품을 저장합니다 (생성 또는 수정).
     *
     * @param product 저장할 상품
     * @return 저장된 상품
     */
    fun save(product: Product): Product

    /**
     * 여러 상품을 일괄 저장합니다.
     *
     * @param products 저장할 상품 목록
     * @return 저장된 상품 목록
     */
    fun saveAll(products: Iterable<Product>): List<Product>
}

package com.groom.product.application.service

import com.groom.product.application.dto.GetProductForOrderQuery
import com.groom.product.application.dto.GetProductsForOrderQuery
import com.groom.product.application.dto.ProductForOrderResult
import com.groom.product.common.exception.ProductException
import com.groom.product.domain.port.LoadProductPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Internal API - 주문 서비스를 위한 상품 조회 Application Service.
 *
 * 다른 도메인 서비스(Order Service 등)가 필요로 하는 상품 정보를 제공합니다.
 * Consumer-Driven Contract 패턴에 따라 Consumer가 필요로 하는 필드만 반환합니다.
 */
@Service
@Transactional(readOnly = true)
class GetProductForOrderService(
    private val loadProductPort: LoadProductPort,
) {
    /**
     * 상품 ID로 상품 단건 조회.
     *
     * @param query 조회 쿼리 (상품 ID)
     * @return 주문에 필요한 상품 정보
     * @throws ProductException.ProductNotFound 상품을 찾을 수 없는 경우
     */
    fun getProductById(query: GetProductForOrderQuery): ProductForOrderResult {
        val product =
            loadProductPort.loadById(query.productId)
                ?: throw ProductException.ProductNotFound(query.productId)

        return ProductForOrderResult.from(product)
    }

    /**
     * 상품 ID 목록으로 상품 다건 조회.
     *
     * @param query 조회 쿼리 (상품 ID 목록)
     * @return 주문에 필요한 상품 정보 목록 (존재하는 상품만 반환)
     */
    fun getProductsByIds(query: GetProductsForOrderQuery): List<ProductForOrderResult> {
        if (query.productIds.isEmpty()) {
            return emptyList()
        }

        val products = loadProductPort.loadAllById(query.productIds)
        return products.map { ProductForOrderResult.from(it) }
    }
}

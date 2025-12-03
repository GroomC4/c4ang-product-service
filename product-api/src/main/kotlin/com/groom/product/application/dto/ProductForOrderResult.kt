package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import java.math.BigDecimal

/**
 * Internal API - 주문을 위한 상품 정보 Result.
 *
 * Consumer(Order Service)가 필요로 하는 필드만 포함합니다.
 *
 * @property id 상품 ID
 * @property storeId 스토어 ID
 * @property name 상품명
 * @property storeName 스토어명
 * @property price 가격
 */
data class ProductForOrderResult(
    val id: String,
    val storeId: String,
    val name: String,
    val storeName: String,
    val price: BigDecimal,
) {
    companion object {
        fun from(product: Product): ProductForOrderResult =
            ProductForOrderResult(
                id = product.id.toString(),
                storeId = product.storeId.toString(),
                name = product.name,
                storeName = product.storeName,
                price = product.price,
            )
    }
}

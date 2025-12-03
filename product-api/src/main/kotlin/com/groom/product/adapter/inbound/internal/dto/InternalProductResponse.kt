package com.groom.product.adapter.inbound.internal.dto

import com.groom.product.application.dto.ProductForOrderResult
import java.math.BigDecimal

/**
 * Internal API용 상품 응답 DTO.
 *
 * Consumer(Order Service 등)가 필요로 하는 필드만 포함합니다.
 * Consumer-Driven Contract 패턴에 따라 Consumer의 요구사항에 맞게 정의됩니다.
 *
 * @property id 상품 ID
 * @property storeId 스토어 ID
 * @property name 상품명
 * @property storeName 스토어명
 * @property price 가격
 */
data class InternalProductResponse(
    val id: String,
    val storeId: String,
    val name: String,
    val storeName: String,
    val price: BigDecimal,
) {
    companion object {
        fun from(result: ProductForOrderResult): InternalProductResponse =
            InternalProductResponse(
                id = result.id,
                storeId = result.storeId,
                name = result.name,
                storeName = result.storeName,
                price = result.price,
            )
    }
}

/**
 * 상품을 찾을 수 없을 때의 에러 응답 DTO.
 */
data class ProductNotFoundErrorResponse(
    val error: String = "PRODUCT_NOT_FOUND",
    val message: String,
)

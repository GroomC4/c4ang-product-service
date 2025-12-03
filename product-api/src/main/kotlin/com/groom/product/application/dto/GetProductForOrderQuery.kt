package com.groom.product.application.dto

import java.util.UUID

/**
 * Internal API - 주문을 위한 상품 단건 조회 Query.
 *
 * @property productId 상품 ID
 */
data class GetProductForOrderQuery(
    val productId: UUID,
)

package com.groom.product.application.dto

import java.util.UUID

/**
 * Internal API - 주문을 위한 상품 다건 조회 Query.
 *
 * @property productIds 상품 ID 목록
 */
data class GetProductsForOrderQuery(
    val productIds: List<UUID>,
)

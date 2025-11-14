package com.groom.product.application.dto

import java.util.UUID

/**
 * 상품 상세 조회 Query DTO.
 *
 * @property productId 조회할 상품 ID
 */
data class GetProductDetailQuery(
    val productId: UUID,
)

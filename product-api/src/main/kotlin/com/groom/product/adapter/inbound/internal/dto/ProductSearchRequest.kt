package com.groom.product.adapter.inbound.internal.dto

import java.util.UUID

/**
 * Internal API용 상품 다건 조회 요청 DTO.
 *
 * @property ids 상품 ID 목록
 */
data class ProductSearchRequest(
    val ids: List<UUID>,
)

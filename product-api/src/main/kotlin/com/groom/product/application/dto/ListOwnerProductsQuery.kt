package com.groom.product.application.dto

import java.util.UUID

/**
 * 판매자 상품 목록 조회 Query DTO.
 *
 * 판매자가 자신의 모든 상품을 조회할 때 사용합니다.
 * 숨김/삭제 상태를 포함한 모든 상품을 조회할 수 있습니다.
 */
data class ListOwnerProductsQuery(
    val storeId: UUID,
    val page: Int = 0,
    val size: Int = 10,
    val sortBy: String = "created_at", // created_at, name, price, stock
    val sortOrder: String = "desc", // asc, desc
    val includeDeleted: Boolean = false, // 삭제된 상품 포함 여부
)

package com.groom.product.adapter.inbound.internal.dto.rag

import java.math.BigDecimal
import java.util.UUID

/**
 * RAG 서비스용 향수 검색 요청 DTO (v2).
 *
 * @property query 검색 쿼리 (키워드)
 * @property scentFamily 향조 필터 (플로랄, 우디, 시트러스, 오리엔탈, 프레시, 스파이시 등)
 * @property season 계절 (봄, 여름, 가을, 겨울)
 * @property brand 브랜드
 * @property gender 성별 (남성, 여성, 유니섹스)
 * @property priceMin 최소 가격
 * @property priceMax 최대 가격
 * @property productIds 특정 ID 목록 조회 (제공 시 다른 필터 무시)
 */
data class RagProductSearchRequest(
    val query: String? = null,
    val scentFamily: String? = null,
    val season: String? = null,
    val brand: String? = null,
    val gender: String? = null,
    val priceMin: BigDecimal? = null,
    val priceMax: BigDecimal? = null,
    val productIds: List<UUID>? = null,
)

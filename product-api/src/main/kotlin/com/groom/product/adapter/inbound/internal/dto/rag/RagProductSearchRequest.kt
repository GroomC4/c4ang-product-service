package com.groom.product.adapter.inbound.internal.dto.rag

import java.math.BigDecimal

/**
 * RAG 서비스용 향수 검색 요청 DTO.
 *
 * @property query 검색 쿼리 (자연어)
 * @property scentFamily 향조 필터 (플로랄, 우디, 시트러스, 오리엔탈, 프레시, 스파이시 등)
 * @property occasion 사용 상황 (데이트, 출근, 캐주얼, 파티, 일상 등)
 * @property season 계절 (봄, 여름, 가을, 겨울)
 * @property brand 브랜드
 * @property gender 성별 (남성, 여성, 유니섹스)
 * @property priceMin 최소 가격
 * @property priceMax 최대 가격
 * @property topK 결과 개수 (기본값: 5)
 */
data class RagProductSearchRequest(
    val query: String,
    val scentFamily: String? = null,
    val occasion: String? = null,
    val season: String? = null,
    val brand: String? = null,
    val gender: String? = null,
    val priceMin: BigDecimal? = null,
    val priceMax: BigDecimal? = null,
    val topK: Int = 5,
)

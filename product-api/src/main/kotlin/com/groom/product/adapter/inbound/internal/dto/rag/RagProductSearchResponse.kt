package com.groom.product.adapter.inbound.internal.dto.rag

import java.math.BigDecimal

/**
 * RAG 서비스용 향수 검색 결과 응답 DTO.
 *
 * @property results 검색 결과 목록
 * @property totalCount 전체 결과 수
 */
data class RagProductSearchResponse(
    val results: List<RagProductSummary>,
    val totalCount: Int,
)

/**
 * RAG 서비스용 향수 요약 정보 DTO.
 *
 * @property id 향수 고유 ID
 * @property brand 브랜드명
 * @property name 향수명
 * @property concentration 농도 (Parfum/EDP/EDT/EDC)
 * @property mainAccords 주요 향조
 * @property description 설명
 * @property price 가격
 * @property similarityScore 유사도 점수 (0~1)
 */
data class RagProductSummary(
    val id: String,
    val brand: String,
    val name: String,
    val concentration: String? = null,
    val mainAccords: String? = null,
    val description: String? = null,
    val price: BigDecimal? = null,
    val similarityScore: Double? = null,
)

package com.groom.product.adapter.inbound.internal.dto.rag

import java.math.BigDecimal

/**
 * RAG 서비스용 향수 비교 결과 응답 DTO.
 *
 * @property comparisonCount 비교된 향수 수
 * @property products 비교 대상 향수 목록
 */
data class RagCompareProductsResponse(
    val comparisonCount: Int,
    val products: List<RagCompareProduct>,
)

/**
 * RAG 서비스용 비교 향수 정보 DTO.
 *
 * @property id 향수 고유 ID
 * @property brand 브랜드명
 * @property name 향수명
 * @property concentration 농도 (Parfum/EDP/EDT/EDC)
 * @property mainAccords 주요 향조
 * @property topNotes 탑노트
 * @property middleNotes 미들노트
 * @property baseNotes 베이스노트
 * @property price 가격
 */
data class RagCompareProduct(
    val id: String,
    val brand: String,
    val name: String,
    val concentration: String? = null,
    val mainAccords: String? = null,
    val topNotes: String? = null,
    val middleNotes: String? = null,
    val baseNotes: String? = null,
    val price: BigDecimal? = null,
)

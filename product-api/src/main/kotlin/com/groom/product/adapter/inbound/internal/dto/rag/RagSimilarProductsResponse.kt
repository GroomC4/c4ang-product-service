package com.groom.product.adapter.inbound.internal.dto.rag

import java.math.BigDecimal

/**
 * RAG 서비스용 유사 향수 검색 결과 응답 DTO.
 *
 * @property baseProductId 기준 향수 ID
 * @property similarProducts 유사 향수 목록
 */
data class RagSimilarProductsResponse(
    val baseProductId: String,
    val similarProducts: List<RagSimilarProduct>,
)

/**
 * RAG 서비스용 유사 향수 정보 DTO.
 *
 * @property id 향수 고유 ID
 * @property brand 브랜드명
 * @property name 향수명
 * @property mainAccords 주요 향조
 * @property price 가격
 * @property similarityScore 유사도 점수 (0~1)
 */
data class RagSimilarProduct(
    val id: String,
    val brand: String,
    val name: String,
    val mainAccords: String? = null,
    val price: BigDecimal? = null,
    val similarityScore: Double,
)

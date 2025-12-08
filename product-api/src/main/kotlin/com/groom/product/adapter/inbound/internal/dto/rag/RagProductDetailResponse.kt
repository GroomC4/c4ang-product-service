package com.groom.product.adapter.inbound.internal.dto.rag

import java.math.BigDecimal

/**
 * RAG 서비스용 향수 상세 정보 응답 DTO.
 *
 * @property id 향수 고유 ID
 * @property brand 브랜드명
 * @property name 향수명
 * @property concentration 농도 (Parfum/EDP/EDT/EDC)
 * @property mainAccords 주요 향조
 * @property topNotes 탑노트
 * @property middleNotes 미들노트
 * @property baseNotes 베이스노트
 * @property description 향수 설명
 * @property price 가격
 * @property imageUrl 이미지 URL
 * @property detailUrl 상세 페이지 링크
 */
data class RagProductDetailResponse(
    val id: String,
    val brand: String,
    val name: String,
    val concentration: String? = null,
    val mainAccords: String? = null,
    val topNotes: String? = null,
    val middleNotes: String? = null,
    val baseNotes: String? = null,
    val description: String? = null,
    val price: BigDecimal? = null,
    val imageUrl: String? = null,
    val detailUrl: String? = null,
)

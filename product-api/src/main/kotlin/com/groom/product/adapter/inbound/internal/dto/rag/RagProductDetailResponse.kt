package com.groom.product.adapter.inbound.internal.dto.rag

import com.groom.product.domain.model.Perfume

/**
 * RAG 서비스용 향수 상세 정보 응답 DTO (v2).
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
 * @property gender 성별 타겟
 * @property seasonScore 계절 점수
 * @property dayNightScore 주야간 점수
 * @property sizes 용량별 가격 정보
 * @property imageUrl 이미지 URL
 * @property detailUrl 상세 페이지 링크
 */
data class RagProductDetailResponse(
    val id: String,
    val brand: String,
    val name: String,
    val concentration: String? = null,
    val mainAccords: List<String>? = null,
    val topNotes: List<String>? = null,
    val middleNotes: List<String>? = null,
    val baseNotes: List<String>? = null,
    val description: String? = null,
    val gender: String? = null,
    val seasonScore: SeasonScore? = null,
    val dayNightScore: DayNightScore? = null,
    val sizes: List<PerfumeSize>? = null,
    val imageUrl: String? = null,
    val detailUrl: String? = null,
) {
    companion object {
        fun from(perfume: Perfume): RagProductDetailResponse =
            RagProductDetailResponse(
                id = perfume.id.toString(),
                brand = perfume.brand,
                name = perfume.name,
                concentration = perfume.concentration,
                mainAccords = perfume.mainAccords?.parseJsonArray(),
                topNotes = perfume.topNotes?.parseJsonArray(),
                middleNotes = perfume.middleNotes?.parseJsonArray(),
                baseNotes = perfume.baseNotes?.parseJsonArray(),
                description = null,
                gender = perfume.gender,
                seasonScore = perfume.seasonScore?.parseSeasonScore(),
                dayNightScore = perfume.dayNightScore?.parseDayNightScore(),
                sizes = perfume.sizes?.parseSizes(),
                imageUrl = null,
                detailUrl = perfume.detailUrl,
            )
    }
}

/**
 * 계절 점수 DTO.
 */
data class SeasonScore(
    val spring: Double? = null,
    val summer: Double? = null,
    val fall: Double? = null,
    val winter: Double? = null,
)

/**
 * 주야간 점수 DTO.
 */
data class DayNightScore(
    val day: Double? = null,
    val night: Double? = null,
)

/**
 * seasonScore JSON 문자열을 SeasonScore로 파싱합니다.
 */
internal fun String.parseSeasonScore(): SeasonScore? =
    try {
        com.fasterxml.jackson.module.kotlin.jacksonObjectMapper().readValue(this, SeasonScore::class.java)
    } catch (e: Exception) {
        null
    }

/**
 * dayNightScore JSON 문자열을 DayNightScore로 파싱합니다.
 */
internal fun String.parseDayNightScore(): DayNightScore? =
    try {
        com.fasterxml.jackson.module.kotlin.jacksonObjectMapper().readValue(this, DayNightScore::class.java)
    } catch (e: Exception) {
        null
    }

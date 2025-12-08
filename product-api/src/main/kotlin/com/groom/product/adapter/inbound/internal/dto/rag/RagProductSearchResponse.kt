package com.groom.product.adapter.inbound.internal.dto.rag

import com.groom.product.common.util.NoteTranslator
import com.groom.product.domain.model.Perfume
import java.math.BigDecimal

/**
 * RAG 서비스용 향수 검색 결과 응답 DTO (v2).
 *
 * @property results 검색 결과 목록
 * @property totalCount 전체 결과 수
 */
data class RagProductSearchResponse(
    val results: List<RagProductSummary>,
    val totalCount: Int,
) {
    companion object {
        fun from(
            perfumes: List<Perfume>,
            translateToKorean: Boolean = true,
        ): RagProductSearchResponse =
            RagProductSearchResponse(
                results = perfumes.map { RagProductSummary.from(it, translateToKorean) },
                totalCount = perfumes.size,
            )
    }
}

/**
 * RAG 서비스용 향수 요약 정보 DTO (v2).
 *
 * @property id 향수 고유 ID
 * @property brand 브랜드명
 * @property name 향수명
 * @property concentration 농도 (Parfum/EDP/EDT/EDC)
 * @property mainAccords 주요 향조
 * @property description 설명
 * @property gender 성별 타겟
 * @property imageUrl 이미지 URL
 * @property sizes 용량별 가격 정보
 */
data class RagProductSummary(
    val id: String,
    val brand: String,
    val name: String,
    val concentration: String? = null,
    val mainAccords: List<String>? = null,
    val description: String? = null,
    val gender: String? = null,
    val imageUrl: String? = null,
    val sizes: List<PerfumeSize>? = null,
) {
    companion object {
        fun from(
            perfume: Perfume,
            translateToKorean: Boolean = true,
        ): RagProductSummary {
            val mainAccords = perfume.mainAccords?.parseJsonArray()

            return RagProductSummary(
                id = perfume.id.toString(),
                brand = perfume.brand,
                name = perfume.name,
                concentration = perfume.concentration,
                mainAccords =
                    if (translateToKorean) {
                        mainAccords?.let { NoteTranslator.toKoreanList(it) }
                    } else {
                        mainAccords
                    },
                description = null,
                gender = perfume.gender,
                imageUrl = null,
                sizes = perfume.sizes?.parseSizes(),
            )
        }
    }
}

/**
 * 향수 용량 및 가격 정보 DTO.
 *
 * @property sizeMl 용량 (ml)
 * @property price 가격
 */
data class PerfumeSize(
    val sizeMl: Int,
    val price: BigDecimal,
)

/**
 * JSON 문자열을 List<String>으로 파싱합니다.
 */
internal fun String.parseJsonArray(): List<String>? =
    try {
        com.fasterxml.jackson.module.kotlin
            .jacksonObjectMapper()
            .readValue(this, Array<String>::class.java)
            .toList()
    } catch (e: Exception) {
        null
    }

/**
 * sizes JSON 문자열을 List<PerfumeSize>로 파싱합니다.
 */
internal fun String.parseSizes(): List<PerfumeSize>? =
    try {
        val mapper = com.fasterxml.jackson.module.kotlin.jacksonObjectMapper()
        val typeRef = mapper.typeFactory.constructCollectionType(List::class.java, PerfumeSize::class.java)
        mapper.readValue(this, typeRef)
    } catch (e: Exception) {
        null
    }

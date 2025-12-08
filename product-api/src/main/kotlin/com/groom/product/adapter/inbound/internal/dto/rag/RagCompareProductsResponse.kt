package com.groom.product.adapter.inbound.internal.dto.rag

import com.groom.product.common.util.NoteTranslator
import com.groom.product.domain.model.Perfume

/**
 * RAG 서비스용 향수 비교 결과 응답 DTO (v2).
 *
 * @property comparisonCount 비교된 향수 수
 * @property products 비교 대상 향수 목록
 */
data class RagCompareProductsResponse(
    val comparisonCount: Int,
    val products: List<RagCompareProduct>,
) {
    companion object {
        fun from(
            perfumes: List<Perfume>,
            translateToKorean: Boolean = true,
        ): RagCompareProductsResponse =
            RagCompareProductsResponse(
                comparisonCount = perfumes.size,
                products = perfumes.map { RagCompareProduct.from(it, translateToKorean) },
            )
    }
}

/**
 * RAG 서비스용 비교 향수 정보 DTO (v2).
 *
 * @property id 향수 고유 ID
 * @property brand 브랜드명
 * @property name 향수명
 * @property concentration 농도 (Parfum/EDP/EDT/EDC)
 * @property mainAccords 주요 향조
 * @property topNotes 탑노트
 * @property middleNotes 미들노트
 * @property baseNotes 베이스노트
 * @property gender 성별 타겟
 * @property sizes 용량별 가격 정보
 */
data class RagCompareProduct(
    val id: String,
    val brand: String,
    val name: String,
    val concentration: String? = null,
    val mainAccords: List<String>? = null,
    val topNotes: List<String>? = null,
    val middleNotes: List<String>? = null,
    val baseNotes: List<String>? = null,
    val gender: String? = null,
    val sizes: List<PerfumeSize>? = null,
) {
    companion object {
        fun from(
            perfume: Perfume,
            translateToKorean: Boolean = true,
        ): RagCompareProduct {
            val mainAccords = perfume.mainAccords?.parseJsonArray()
            val topNotes = perfume.topNotes?.parseJsonArray()
            val middleNotes = perfume.middleNotes?.parseJsonArray()
            val baseNotes = perfume.baseNotes?.parseJsonArray()

            return RagCompareProduct(
                id = perfume.id.toString(),
                brand = perfume.brand,
                name = perfume.name,
                concentration = perfume.concentration,
                mainAccords = mainAccords?.translateIfNeeded(translateToKorean),
                topNotes = topNotes?.translateIfNeeded(translateToKorean),
                middleNotes = middleNotes?.translateIfNeeded(translateToKorean),
                baseNotes = baseNotes?.translateIfNeeded(translateToKorean),
                gender = perfume.gender,
                sizes = perfume.sizes?.parseSizes(),
            )
        }

        private fun List<String>.translateIfNeeded(translateToKorean: Boolean): List<String> =
            if (translateToKorean) NoteTranslator.toKoreanList(this) else this
    }
}

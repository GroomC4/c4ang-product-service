package com.groom.product.adapter.inbound.web.dto

import com.groom.product.application.dto.GenerateProductDescriptionResult

/**
 * 상품 설명 생성 응답 DTO.
 *
 * @property description AI가 생성한 상품 설명 (최대 500자)
 */
data class GenerateProductDescriptionResponse(
    val description: String,
) {
    companion object {
        fun from(result: GenerateProductDescriptionResult): GenerateProductDescriptionResponse =
            GenerateProductDescriptionResponse(
                description = result.description,
            )
    }
}

package com.groom.product.adapter.inbound.web.dto

import com.groom.product.application.dto.GenerateProductDescriptionCommand
import java.util.UUID

/**
 * 상품 설명 생성 요청 DTO.
 *
 * @property prompt 상품 설명 생성을 위한 프롬프트 (최대 100자)
 */
data class GenerateProductDescriptionRequest(
    val prompt: String,
) {
    fun toCommand(userId: UUID): GenerateProductDescriptionCommand =
        GenerateProductDescriptionCommand(
            userId = userId,
            prompt = prompt,
        )
}

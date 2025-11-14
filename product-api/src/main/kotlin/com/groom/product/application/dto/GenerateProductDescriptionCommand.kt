package com.groom.product.application.dto

import java.util.UUID

/**
 * 상품 설명 생성 Command DTO.
 *
 * AI를 활용하여 상품 설명을 자동 생성합니다.
 *
 * @property userId 요청한 사용자 ID (OWNER 역할 필요)
 * @property prompt 상품 설명 생성을 위한 프롬프트 (최대 100자)
 */
data class GenerateProductDescriptionCommand(
    val userId: UUID,
    val prompt: String,
)

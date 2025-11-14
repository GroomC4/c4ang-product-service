package com.groom.product.application.dto

/**
 * 상품 설명 생성 결과 DTO.
 *
 * @property description AI가 생성한 상품 설명 (최대 500자)
 */
data class GenerateProductDescriptionResult(
    val description: String,
)

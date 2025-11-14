package com.groom.product.domain.model

/**
 * 상품명 값 객체.
 * 상품명은 비어있을 수 없으며, 최소 2자 이상, 최대 100자 이하여야 합니다.
 */
data class ProductName(
    val value: String,
) {
    init {
        val trimmed = value.trim()
        require(trimmed.isNotBlank()) { "상품명은 비어있을 수 없습니다." }
        require(trimmed.length >= MIN_LENGTH) {
            "상품명은 ${MIN_LENGTH}자 이상이어야 합니다. (입력값: ${trimmed.length}자)"
        }
        require(trimmed.length <= MAX_LENGTH) {
            "상품명은 ${MAX_LENGTH}자 이하여야 합니다. (입력값: ${trimmed.length}자)"
        }
    }

    companion object {
        private const val MIN_LENGTH = 2
        private const val MAX_LENGTH = 100

        fun from(value: String): ProductName {
            val normalized = value.trim()
            return ProductName(normalized)
        }
    }
}
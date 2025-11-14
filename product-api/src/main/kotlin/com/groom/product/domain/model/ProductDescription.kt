package com.groom.product.domain.model

/**
 * 상품 설명 값 객체.
 * 상품 설명은 비어있을 수 있으나, 최대 2000자를 초과할 수 없습니다.
 */
data class ProductDescription(
    val value: String?,
) {
    init {
        value?.let {
            val trimmed = it.trim()
            require(trimmed.length <= MAX_LENGTH) {
                "상품 설명은 ${MAX_LENGTH}자를 초과할 수 없습니다. (입력값: ${trimmed.length}자)"
            }
        }
    }

    companion object {
        private const val MAX_LENGTH = 2000

        fun from(value: String?): ProductDescription {
            val normalized = value?.trim()?.takeIf { it.isNotEmpty() }
            return ProductDescription(normalized)
        }

        fun empty(): ProductDescription = ProductDescription(null)
    }

    fun isEmpty(): Boolean = value.isNullOrEmpty()

    fun isNotEmpty(): Boolean = !isEmpty()
}
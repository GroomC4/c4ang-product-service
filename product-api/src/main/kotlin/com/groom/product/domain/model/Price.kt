package com.groom.product.domain.model

import java.math.BigDecimal

/**
 * 상품 가격 값 객체.
 * 가격은 0원 이상이어야 하며, 소수점 이하는 허용하지 않습니다.
 */
data class Price(
    val value: BigDecimal,
) {
    init {
        require(value >= BigDecimal.ZERO) { "가격은 0원 이상이어야 합니다. (입력값: $value)" }
        require(value.scale() <= 0) { "가격은 정수여야 합니다. (입력값: $value)" }
    }

    companion object {
        fun from(value: BigDecimal): Price {
            // 소수점 이하를 제거하고 정수로 변환
            val integerValue = value.setScale(0, java.math.RoundingMode.DOWN)
            return Price(integerValue)
        }

        fun from(value: Long): Price = Price(BigDecimal.valueOf(value))

        fun from(value: Int): Price = Price(BigDecimal.valueOf(value.toLong()))
    }
}
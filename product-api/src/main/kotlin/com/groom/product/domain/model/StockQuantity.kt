package com.groom.product.domain.model

/**
 * 재고 수량 값 객체.
 * 재고는 0개 이상이어야 합니다.
 */
data class StockQuantity(
    val value: Int,
) {
    init {
        require(value >= 0) { "재고 수량은 0개 이상이어야 합니다. (입력값: $value)" }
    }

    companion object {
        fun from(value: Int): StockQuantity = StockQuantity(value)

        fun zero(): StockQuantity = StockQuantity(0)
    }

    fun isOutOfStock(): Boolean = value == 0

    fun hasStock(): Boolean = value > 0

    fun decrease(amount: Int): StockQuantity {
        require(amount > 0) { "감소량은 양수여야 합니다. (입력값: $amount)" }
        require(value >= amount) { "재고가 부족합니다. (현재: $value, 요청: $amount)" }
        return StockQuantity(value - amount)
    }

    fun increase(amount: Int): StockQuantity {
        require(amount > 0) { "증가량은 양수여야 합니다. (입력값: $amount)" }
        return StockQuantity(value + amount)
    }
}
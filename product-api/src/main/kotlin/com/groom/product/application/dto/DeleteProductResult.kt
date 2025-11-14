package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import java.time.LocalDateTime

/**
 * 상품 삭제 Result DTO.
 */
data class DeleteProductResult(
    val productId: String,
    val storeId: String,
    val name: String,
    val deletedAt: LocalDateTime,
) {
    companion object {
        fun from(product: Product): DeleteProductResult =
            DeleteProductResult(
                productId = product.id.toString(),
                storeId = product.storeId.toString(),
                name = product.name,
                deletedAt = product.deletedAt ?: LocalDateTime.now(),
            )
    }
}

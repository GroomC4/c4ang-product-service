package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * 판매자 상품 목록 조회 Result DTO.
 */
data class ListOwnerProductsResult(
    val totalElements: Long,
    val totalPages: Int,
    val currentPage: Int,
    val pageSize: Int,
    val products: List<OwnerProductSummary>,
) {
    /**
     * 판매자용 상품 요약 정보.
     *
     * 공개 조회와 달리 숨김/삭제 상태 등의 관리 정보를 포함합니다.
     */
    data class OwnerProductSummary(
        val productId: String,
        val name: String,
        val price: BigDecimal,
        val stockQuantity: Int,
        val status: String,
        val isHidden: Boolean,
        val isDeleted: Boolean,
        val thumbnailUrl: String?,
        val categoryId: String?,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?,
    ) {
        companion object {
            fun from(product: Product): OwnerProductSummary =
                OwnerProductSummary(
                    productId = product.id.toString(),
                    name = product.name,
                    price = product.price,
                    stockQuantity = product.stockQuantity,
                    status = product.status,
                    isHidden = product.hiddenAt != null,
                    isDeleted = product.deletedAt != null,
                    thumbnailUrl = product.thumbnailUrl,
                    categoryId = product.categoryId?.toString(),
                    createdAt = product.createdAt,
                    updatedAt = product.updatedAt,
                )
        }
    }
}

package com.groom.product.application.dto

import com.groom.product.domain.model.Product
import com.groom.product.domain.model.ProductImage
import java.math.BigDecimal

/**
 * 상품 상세 조회 Result DTO.
 *
 * @property id 상품 ID
 * @property name 상품명
 * @property description 상품 설명
 * @property price 가격
 * @property stockQuantity 재고 수량
 * @property status 판매 상태 (ON_SALE, OFF_SHELF)
 * @property categoryId 카테고리 ID
 * @property categoryPath 카테고리 경로 (예: "전자제품 > 컴퓨터 주변기기 > 마우스")
 * @property store 스토어 정보
 * @property images 상품 이미지 목록
 * @property rating 평균 평점 (추후 구현)
 * @property reviewCount 리뷰 개수 (추후 구현)
 * @property createdAt 생성일시
 */
data class GetProductDetailResult(
    val id: String,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val stockQuantity: Int,
    val status: String,
    val categoryId: String,
    val categoryPath: String?,
    val store: StoreInfo,
    val images: List<ProductImageInfo>,
    val rating: BigDecimal? = null, // 추후 Review 도메인에서 집계
    val reviewCount: Int = 0, // 추후 Review 도메인에서 집계
    val createdAt: String,
) {
    data class StoreInfo(
        val id: String,
        val name: String,
    )

    data class ProductImageInfo(
        val imageUrl: String,
        val imageType: String,
    ) {
        companion object {
            fun from(image: ProductImage): ProductImageInfo =
                ProductImageInfo(
                    imageUrl = image.imageUrl,
                    imageType = image.imageType,
                )
        }
    }

    companion object {
        fun from(
            product: Product,
            categoryPath: String?,
        ): GetProductDetailResult =
            GetProductDetailResult(
                id = product.id.toString(),
                name = product.name,
                description = product.description,
                price = product.price,
                stockQuantity = product.stockQuantity,
                status = product.status,
                categoryId = product.categoryId.toString(),
                categoryPath = categoryPath,
                store = StoreInfo(
                    id = product.storeId.toString(),
                    name = product.storeName,
                ),
                images = product.images.map { ProductImageInfo.from(it) },
                createdAt = product.createdAt.toString(),
            )
    }
}

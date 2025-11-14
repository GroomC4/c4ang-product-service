package com.groom.product.domain.service

import com.groom.product.domain.model.Product
import com.groom.product.domain.model.ProductImage
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.UUID

/**
 * Product 애그리게이트의 복잡한 생성 로직을 담당하는 팩토리.
 * Product와 ProductImage의 일관성 있는 생성과 관계 설정을 보장한다.
 */
@Component
class ProductFactory {
    /**
     * 이미지가 포함된 새로운 상품을 생성한다.
     * Product와 ProductImage를 함께 생성하고 관계를 설정한다.
     *
     * @param storeId 스토어 UUID
     * @param storeName 스토어 이름 (비정규화, CQRS 패턴)
     * @param categoryId 카테고리 UUID
     * @param name 상품명
     * @param price 가격
     * @param stockQuantity 재고 수량
     * @param thumbnailUrl 썸네일 URL
     * @param description 상품 설명
     * @param images 상품 이미지 정보 목록
     * @return 생성된 Product 엔티티 (ProductImage가 포함됨)
     */
    fun createNewProduct(
        storeId: UUID,
        storeName: String,
        categoryId: UUID,
        name: String,
        price: BigDecimal,
        stockQuantity: Int,
        thumbnailUrl: String?,
        description: String?,
        images: List<ImageData>,
    ): Product {
        val product =
            Product(
                storeId = storeId,
                storeName = storeName,
                categoryId = categoryId,
                name = name,
                status = "ON_SALE",
                price = price,
                stockQuantity = stockQuantity,
                thumbnailUrl = thumbnailUrl,
                description = description,
            )

        images.forEach { imageData ->
            val image =
                ProductImage(
                    imageType = imageData.imageType,
                    imageUrl = imageData.imageUrl,
                    displayOrder = imageData.displayOrder,
                )
            product.addImage(image)
        }

        return product
    }

    /**
     * 이미지 데이터 전달용 DTO.
     */
    data class ImageData(
        val imageType: String,
        val imageUrl: String,
        val displayOrder: Int,
    )
}

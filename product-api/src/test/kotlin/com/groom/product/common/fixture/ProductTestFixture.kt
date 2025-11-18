package com.groom.product.common.fixture

import com.groom.product.domain.model.Product
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

/**
 * Product 엔티티 테스트 픽스처
 *
 * JPA Auditing으로 설정되는 createdAt/updatedAt 같은 필드를
 * 리플렉션으로 초기화하여 테스트용 Product 객체를 생성합니다.
 */
object ProductTestFixture {
    /**
     * 기본 Product 생성
     */
    fun createProduct(
        id: UUID = UUID.randomUUID(),
        storeId: UUID = UUID.randomUUID(),
        storeName: String = "Test Store",
        categoryId: UUID = UUID.randomUUID(),
        name: String = "Test Product",
        status: String = "ON_SALE",
        price: BigDecimal = BigDecimal("10000"),
        stockQuantity: Int = 100,
        thumbnailUrl: String? = null,
        description: String? = "Test Product Description",
        hiddenAt: LocalDateTime? = null,
        deletedAt: LocalDateTime? = null,
        createdAt: LocalDateTime = LocalDateTime.now(),
        updatedAt: LocalDateTime = LocalDateTime.now(),
    ): Product {
        val product =
            Product(
                storeId = storeId,
                storeName = storeName,
                categoryId = categoryId,
                name = name,
                status = status,
                price = price,
                stockQuantity = stockQuantity,
                thumbnailUrl = thumbnailUrl,
                description = description,
                hiddenAt = hiddenAt,
                deletedAt = deletedAt,
            )

        // 리플렉션으로 protected 필드 설정
        setField(product, "id", id)
        setField(product, "createdAt", createdAt)
        setField(product, "updatedAt", updatedAt)

        return product
    }

    /**
     * 판매중인 Product 생성
     */
    fun createOnSaleProduct(
        storeId: UUID = UUID.randomUUID(),
        name: String = "On Sale Product",
        price: BigDecimal = BigDecimal("10000"),
        stockQuantity: Int = 100,
    ): Product =
        createProduct(
            storeId = storeId,
            name = name,
            status = "ON_SALE",
            price = price,
            stockQuantity = stockQuantity,
        )

    /**
     * 품절된 Product 생성
     */
    fun createOutOfStockProduct(
        storeId: UUID = UUID.randomUUID(),
        name: String = "Out of Stock Product",
    ): Product =
        createProduct(
            storeId = storeId,
            name = name,
            status = "ON_SALE",
            stockQuantity = 0,
        )

    /**
     * 판매 중단된 Product 생성
     */
    fun createOffShelfProduct(
        storeId: UUID = UUID.randomUUID(),
        name: String = "Off Shelf Product",
    ): Product =
        createProduct(
            storeId = storeId,
            name = name,
            status = "OFF_SHELF",
        )

    /**
     * 리플렉션으로 필드 설정 (private/protected 필드 접근)
     */
    fun setField(
        target: Any,
        fieldName: String,
        value: Any?,
    ) {
        var clazz: Class<*>? = target.javaClass
        while (clazz != null) {
            try {
                val field = clazz.getDeclaredField(fieldName)
                field.isAccessible = true
                field.set(target, value)
                return
            } catch (e: NoSuchFieldException) {
                clazz = clazz.superclass
            }
        }
        throw NoSuchFieldException("Field $fieldName not found in ${target.javaClass}")
    }
}

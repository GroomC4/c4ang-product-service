package com.groom.product.domain.model

import com.groom.product.configuration.jpa.CreatedAndUpdatedAtAuditEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

/**
 * Product 애그리게이트 루트.
 * DDL: p_product 테이블
 */
@Entity
@Table(name = "p_product")
class Product(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val storeId: UUID,
    @Column(nullable = false)
    var storeName: String,
    categoryId: UUID,
    name: String,
    @Column(nullable = false)
    var status: String = "ON_SALE", // ON_SALE, OFF_SHELF
    price: BigDecimal,
    stockQuantity: Int = 0,
    thumbnailUrl: String? = null,
    description: String? = null,
    hiddenAt: LocalDateTime? = null,
    deletedAt: LocalDateTime? = null,
) : CreatedAndUpdatedAtAuditEntity() {
    @Column(nullable = false)
    var categoryId: UUID = categoryId
        private set

    @Column(name = "product_name", nullable = false)
    var name: String = name
        private set

    @Column(nullable = false, precision = 12, scale = 2)
    var price: BigDecimal = price
        private set

    @Column(nullable = false)
    var stockQuantity: Int = stockQuantity
        private set

    @Column
    var thumbnailUrl: String? = thumbnailUrl
        private set

    @Column(columnDefinition = "text")
    var description: String? = description
        private set

    @Column
    var hiddenAt: LocalDateTime? = hiddenAt
        private set

    @Column
    var deletedAt: LocalDateTime? = deletedAt
        private set

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: MutableList<ProductImage> = mutableListOf()

    fun addImage(image: ProductImage) {
        images.add(image)
        image.product = this
    }

    /**
     * 상품 정보를 수정합니다.
     */
    fun updateInfo(
        newName: String,
        newPrice: BigDecimal,
        newStockQuantity: Int,
        newDescription: String?,
        newThumbnailUrl: String?,
        newCategoryId: UUID,
    ): Boolean {
        require(newName.isNotBlank()) { "Product name cannot be blank" }
        require(newPrice >= BigDecimal.ZERO) { "Price must be non-negative" }
        require(newStockQuantity >= 0) { "Stock quantity must be non-negative" }

        val hasChanges =
            this.name != newName ||
                this.price.compareTo(newPrice) != 0 ||
                this.stockQuantity != newStockQuantity ||
                this.description != newDescription ||
                this.thumbnailUrl != newThumbnailUrl ||
                this.categoryId != newCategoryId

        if (hasChanges) {
            this.name = newName
            this.price = newPrice
            this.stockQuantity = newStockQuantity
            this.description = newDescription
            this.thumbnailUrl = newThumbnailUrl
            this.categoryId = newCategoryId
        }

        return hasChanges
    }

    /**
     * 상품을 소프트 삭제합니다.
     *
     * @return 이미 삭제된 경우 false, 삭제 성공 시 true
     */
    fun delete(): Boolean {
        if (this.deletedAt != null) {
            return false // 이미 삭제됨
        }

        this.deletedAt = LocalDateTime.now()
        this.status = "OFF_SHELF" // 삭제 시 판매 중지
        return true
    }

    /**
     * 상품 숨김/복원을 토글합니다.
     *
     * @return 숨김 처리된 경우 true, 복원된 경우 false
     */
    fun toggleHide(): Boolean {
        this.hiddenAt =
            if (this.hiddenAt == null) {
                LocalDateTime.now() // 숨김 처리
            } else {
                null // 복원
            }
        return this.hiddenAt != null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        if (id == null || other.id == null) return false
        return id == other.id
    }

    override fun hashCode(): Int = id?.hashCode() ?: System.identityHashCode(this)

    override fun toString(): String = "Product(id=$id, name=$name, status=$status, price=$price)"
}

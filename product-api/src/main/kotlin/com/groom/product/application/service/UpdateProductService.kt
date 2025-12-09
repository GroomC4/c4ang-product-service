package com.groom.product.application.service

import com.groom.product.application.dto.UpdateProductCommand
import com.groom.product.application.dto.UpdateProductResult
import com.groom.product.common.exception.StoreException
import com.groom.product.domain.event.DomainEventPublisher
import com.groom.product.domain.event.ProductInfoUpdatedEvent
import com.groom.product.domain.model.Price
import com.groom.product.domain.model.ProductDescription
import com.groom.product.domain.model.ProductName
import com.groom.product.domain.model.StockQuantity
import com.groom.product.domain.model.ThumbnailUrl
import com.groom.product.domain.port.LoadProductPort
import com.groom.product.domain.port.LoadStorePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * 상품 수정 서비스.
 *
 * JPA dirty checking을 활용하여 명시적 save() 없이 트랜잭션 커밋 시 자동 업데이트합니다.
 */
@Service
class UpdateProductService(
    private val loadProductPort: LoadProductPort,
    private val loadStorePort: LoadStorePort,
    private val domainEventPublisher: DomainEventPublisher,
) {
    @Transactional
    fun update(requestUserId: UUID, command: UpdateProductCommand): UpdateProductResult {
        // 1. Value Objects 생성 및 유효성 검사
        val productName = ProductName.from(command.name)
        val price = Price.from(command.price)
        val stockQuantity = StockQuantity.from(command.stockQuantity)
        val thumbnailUrl = command.thumbnailUrl?.let { ThumbnailUrl.from(it) }
        val description = command.description?.let { ProductDescription.from(it) }

        // 2. 상품 존재 여부 확인
        val product =
            loadProductPort.loadById(command.productId)
                ?: throw IllegalArgumentException("Product not found: ${command.productId}")

        // 3. 스토어 소유권 검증 (요청한 사용자가 해당 스토어의 소유자인지 확인)
        loadStorePort.loadByIdAndOwnerId(product.storeId, requestUserId)
            ?: throw StoreException.StoreAccessDenied(
                storeId = product.storeId,
                userId = requestUserId,
            )

        // 4. 상품 정보 업데이트 (도메인 로직)
        val hasChanges =
            product.updateInfo(
                newName = productName.value,
                newPrice = price.value,
                newStockQuantity = stockQuantity.value,
                newDescription = description?.value,
                newThumbnailUrl = thumbnailUrl?.value,
                newCategoryId = command.categoryId,
            )

        // 5. 변경사항이 있으면 이벤트 발행
        if (hasChanges) {
            val event =
                ProductInfoUpdatedEvent(
                    productId = product.id,
                    storeId = product.storeId,
                    categoryId = product.categoryId,
                    name = product.name,
                    price = product.price,
                    stockQuantity = product.stockQuantity,
                    description = product.description,
                    thumbnailUrl = product.thumbnailUrl,
                    status = product.status,
                )
            domainEventPublisher.publish(event)
        }

        // 6. Result 반환 (JPA dirty checking에 의해 자동 업데이트됨)
        return UpdateProductResult.from(product)
    }
}

package com.groom.product.application.service

import com.groom.product.common.domain.DomainEventPublisher
import com.groom.product.application.dto.ToggleProductHideCommand
import com.groom.product.application.dto.ToggleProductHideResult
import com.groom.product.domain.event.ProductHiddenEvent
import com.groom.product.domain.event.ProductRestoredEvent
import com.groom.product.infrastructure.repository.ProductPersistenceAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * 상품 숨김/복원 Service.
 *
 * 판매자가 상품을 숨기거나 복원할 수 있습니다.
 * 숨김 처리된 상품은 목록에서 보이지 않습니다.
 */
@Service
class ToggleProductHideService(
    private val productRepository: ProductPersistenceAdapter,
    private val domainEventPublisher: DomainEventPublisher,
) {
    @Transactional
    fun toggleHide(command: ToggleProductHideCommand): ToggleProductHideResult {
        val product =
            productRepository
                .findById(command.productId)
                .orElseThrow { IllegalArgumentException("Product not found") }

        require(product.storeId == command.storeId) {
            "Product does not belong to this store"
        }

        val isHidden = product.toggleHide()

        if (isHidden) {
            domainEventPublisher.publish(
                ProductHiddenEvent(
                    productId = product.id,
                    storeId = product.storeId,
                    name = product.name,
                    hiddenAt = product.hiddenAt!!,
                    occurredAt = LocalDateTime.now(),
                ),
            )
        } else {
            domainEventPublisher.publish(
                ProductRestoredEvent(
                    productId = product.id,
                    storeId = product.storeId,
                    name = product.name,
                    occurredAt = LocalDateTime.now(),
                ),
            )
        }

        return ToggleProductHideResult.from(product)
    }
}

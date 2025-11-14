package com.groom.product.application.service

import com.groom.product.common.domain.DomainEventPublisher
import com.groom.product.application.dto.DeleteProductCommand
import com.groom.product.application.dto.DeleteProductResult
import com.groom.product.domain.event.ProductDeletedEvent
import com.groom.product.infrastructure.repository.ProductPersistenceAdapter
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 상품 삭제 서비스.
 *
 * 소프트 삭제를 수행하며, JPA dirty checking을 활용하여 자동 업데이트합니다.
 */
@Service
class DeleteProductService(
    private val productRepository: ProductPersistenceAdapter,
    private val domainEventPublisher: DomainEventPublisher,
) {
    @Transactional
    fun delete(command: DeleteProductCommand): DeleteProductResult {
        // 1. 상품 존재 여부 확인
        val product =
            productRepository
                .findById(command.productId)
                .orElseThrow { IllegalArgumentException("Product not found: ${command.productId}") }

        // 2. 스토어 소유권 검증
        require(product.storeId == command.storeId) {
            "Product does not belong to this store"
        }

        // 3. 소프트 삭제 (도메인 로직)
        val deleted = product.delete()
        if (!deleted) {
            throw IllegalStateException("Product is already deleted")
        }

        // 4. 이벤트 발행
        val event =
            ProductDeletedEvent(
                productId = product.id,
                storeId = product.storeId,
                name = product.name,
                deletedAt = product.deletedAt!!,
            )
        domainEventPublisher.publish(event)

        // 5. Result 반환 (JPA dirty checking에 의해 자동 업데이트됨)
        return DeleteProductResult.from(product)
    }
}

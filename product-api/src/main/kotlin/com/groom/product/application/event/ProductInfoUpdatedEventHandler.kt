package com.groom.product.application.event

import com.groom.product.adapter.outbound.persistence.ProductAuditJpaRepository
import com.groom.product.domain.event.ProductInfoUpdatedEvent
import com.groom.product.domain.model.ProductAudit
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

/**
 * ProductInfoUpdatedEvent 핸들러.
 *
 * 상품 수정 이벤트를 처리하여 감사 로그를 생성합니다.
 * - @TransactionalEventListener(AFTER_COMMIT): 메인 트랜잭션 성공 후 실행
 * - @Transactional(REQUIRES_NEW): 독립적인 새 트랜잭션에서 실행
 * - 이벤트 핸들러 실패해도 메인 작업(상품 수정)은 성공 보장
 */
@Component
class ProductInfoUpdatedEventHandler(
    private val productAuditRepository: ProductAuditJpaRepository,
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleProductInfoUpdated(event: ProductInfoUpdatedEvent) {
        val changeSummary = buildChangeSummary(event)

        val audit =
            ProductAudit(
                productId = event.productId,
                actorUserId = null, // TODO: 인증 컨텍스트에서 userId 가져오기
                eventType = "PRODUCT_INFO_UPDATED",
                statusSnapshot = event.status,
                hiddenAtSnapshot = null,
                changeSummary = changeSummary,
                recordedAt = event.occurredAt,
                metadata = null,
            )

        productAuditRepository.save(audit)
    }

    private fun buildChangeSummary(event: ProductInfoUpdatedEvent): String {
        val parts =
            listOf(
                "상품 정보 수정",
                "이름: '${event.name}'",
                "가격: ${event.price}",
                "재고: ${event.stockQuantity}",
            )
        return parts.joinToString(", ")
    }
}

package com.groom.product.application.event

import com.groom.product.domain.event.ProductRegisteredEvent
import com.groom.product.domain.model.ProductAudit
import com.groom.product.infrastructure.repository.ProductAuditRepositoryImpl
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

/**
 * ProductRegisteredEvent 핸들러.
 *
 * 상품 등록 이벤트를 처리하여 감사 로그를 생성합니다.
 * - @TransactionalEventListener(AFTER_COMMIT): 메인 트랜잭션 성공 후 실행
 * - @Transactional(REQUIRES_NEW): 독립적인 새 트랜잭션에서 실행
 * - 이벤트 핸들러 실패해도 메인 작업(상품 등록)은 성공 보장
 */
@Component
class ProductRegisteredEventHandler(
    private val productAuditRepository: ProductAuditRepositoryImpl,
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleProductRegistered(event: ProductRegisteredEvent) {
        val changeSummary = buildChangeSummary(event)

        val audit =
            ProductAudit(
                productId = event.productId,
                actorUserId = null, // TODO: 인증 컨텍스트에서 userId 가져오기
                eventType = "PRODUCT_REGISTERED",
                statusSnapshot = event.status,
                hiddenAtSnapshot = event.hiddenAt,
                changeSummary = changeSummary,
                recordedAt = event.occurredAt,
                metadata = null,
            )

        productAuditRepository.save(audit)
    }

    private fun buildChangeSummary(event: ProductRegisteredEvent): String {
        val parts =
            listOf(
                "상품 등록",
                "이름: '${event.name}'",
                "가격: ${event.price}",
                "재고: ${event.stockQuantity}",
                "상태: ${event.status}",
            )
        return parts.joinToString(", ")
    }
}

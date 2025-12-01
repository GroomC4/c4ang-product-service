package com.groom.product.application.event

import com.groom.product.adapter.out.persistence.ProductAuditJpaRepository
import com.groom.product.domain.event.ProductHiddenEvent
import com.groom.product.domain.model.ProductAudit
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

/**
 * ProductHiddenEvent 핸들러.
 *
 * 상품 숨김 이벤트를 처리하여 감사 로그를 생성합니다.
 * - @TransactionalEventListener(AFTER_COMMIT): 메인 트랜잭션 성공 후 실행
 * - @Transactional(REQUIRES_NEW): 독립적인 새 트랜잭션에서 실행
 * - 이벤트 핸들러 실패해도 메인 작업(상품 숨김)은 성공 보장
 */
@Component
class ProductHiddenEventHandler(
    private val productAuditRepository: ProductAuditJpaRepository,
) {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun handleProductHidden(event: ProductHiddenEvent) {
        val changeSummary = "상품 숨김: '${event.name}'"

        val audit =
            ProductAudit(
                productId = event.productId,
                actorUserId = null, // TODO: 인증 컨텍스트에서 userId 가져오기
                eventType = "PRODUCT_HIDDEN",
                statusSnapshot = null,
                hiddenAtSnapshot = event.hiddenAt,
                changeSummary = changeSummary,
                recordedAt = event.occurredAt,
                metadata = null,
            )

        productAuditRepository.save(audit)
    }
}

package com.groom.product.adapter.outbound.ai

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * AI 프롬프트 감사 로그 서비스.
 *
 * AI API 요청/응답을 독립적인 트랜잭션으로 저장합니다.
 * 메인 비즈니스 로직의 트랜잭션과 분리되어 동작합니다.
 */
@Service
class AiPromptAuditService(
    private val aiPromptAuditRepository: AiPromptAuditRepository,
) {
    /**
     * AI 프롬프트 감사 로그를 저장합니다.
     * 독립적인 트랜잭션으로 실행되므로 메인 트랜잭션 실패 시에도 저장됩니다.
     *
     * @param userId 사용자 ID
     * @param prompt AI에게 전송한 프롬프트
     * @param response AI로부터 받은 응답
     * @param model 사용한 AI 모델명
     * @param success 성공 여부
     * @param errorMessage 실패 시 에러 메시지
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun saveAuditLog(
        userId: UUID,
        prompt: String,
        response: String?,
        model: String,
        success: Boolean,
        errorMessage: String?,
    ) {
        val audit =
            AiPromptAudit(
                userId = userId,
                prompt = prompt,
                response = response,
                model = model,
                success = success,
                errorMessage = errorMessage,
            )
        aiPromptAuditRepository.save(audit)
    }
}

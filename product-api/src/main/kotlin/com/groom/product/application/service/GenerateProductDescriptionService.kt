package com.groom.product.application.service

import com.groom.product.application.dto.GenerateProductDescriptionCommand
import com.groom.product.adapter.outbound.ai.AiPromptAuditService
import com.groom.product.domain.port.ProductDescriptionGenerator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 상품 설명 생성 애플리케이션 서비스.
 *
 * AI를 활용하여 상품 설명을 자동으로 생성합니다.
 * 모든 예외를 처리하고 null을 반환하여 메인 트랜잭션의 롤백을 방지합니다.
 * AI API 요청/응답은 독립적인 트랜잭션으로 감사 로그에 기록됩니다.
 */
@Service
@Transactional(readOnly = true)
class GenerateProductDescriptionService(
    private val descriptionGenerator: ProductDescriptionGenerator,
    private val aiPromptAuditService: AiPromptAuditService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * AI를 사용하여 상품 설명을 생성합니다.
     * 예외 발생 시 null을 반환하며, 메인 트랜잭션은 영향받지 않습니다.
     *
     * @param command 상품 설명 생성 커맨드
     * @return 생성된 상품 설명 (실패 시 null)
     */
    fun generate(command: GenerateProductDescriptionCommand): String? =
        try {
            // 인프라스트럭처를 통한 AI 서비스 호출
            val description = descriptionGenerator.generate(command.prompt)

            // 성공 감사 로그 저장 (독립적인 트랜잭션)
            try {
                aiPromptAuditService.saveAuditLog(
                    userId = command.userId,
                    prompt = command.prompt,
                    response = description,
                    model = "gemini-2.0-flash-exp",
                    success = true,
                    errorMessage = null,
                )
            } catch (auditEx: Exception) {
                // 감사 로그 저장 실패는 무시 (로그만 남김)
                logger.warn("Failed to save AI prompt audit log", auditEx)
            }

            description
        } catch (ex: Exception) {
            // 실패 감사 로그 저장 (독립적인 트랜잭션)
            try {
                aiPromptAuditService.saveAuditLog(
                    userId = command.userId,
                    prompt = command.prompt,
                    response = null,
                    model = "gemini-2.0-flash-exp",
                    success = false,
                    errorMessage = ex.message ?: ex.javaClass.simpleName,
                )
            } catch (auditEx: Exception) {
                // 감사 로그 저장 실패는 무시 (로그만 남김)
                logger.warn("Failed to save AI prompt audit log", auditEx)
            }

            // 예외 발생 시 로그만 남기고 null 반환
            logger.warn("Failed to generate product description: ${ex.message}", ex)
            null
        }
}

package com.groom.product.domain.service

import com.groom.product.domain.port.ProductDescriptionGenerationException
import org.springframework.stereotype.Component

/**
 * 상품 설명 검증 도메인 서비스.
 *
 * 상품 설명 생성과 관련된 비즈니스 규칙을 검증합니다.
 */
@Component
class ProductDescriptionValidator {
    companion object {
        private const val MAX_PROMPT_LENGTH = 100
        private const val MIN_PROMPT_LENGTH = 1
    }

    /**
     * 프롬프트의 유효성을 검증합니다.
     *
     * @param prompt 검증할 프롬프트
     * @throws ProductDescriptionGenerationException.PromptTooLong 프롬프트가 너무 길거나 짧은 경우
     */
    fun validatePrompt(prompt: String) {
        when {
            prompt.length > MAX_PROMPT_LENGTH -> {
                throw ProductDescriptionGenerationException.PromptTooLong(
                    actualLength = prompt.length,
                    maxLength = MAX_PROMPT_LENGTH,
                )
            }
            prompt.length < MIN_PROMPT_LENGTH -> {
                throw ProductDescriptionGenerationException.PromptTooLong(
                    actualLength = prompt.length,
                    maxLength = MAX_PROMPT_LENGTH,
                )
            }
        }
    }
}

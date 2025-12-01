package com.groom.product.common.config

import com.groom.product.common.configuration.GeminiProperties
import com.groom.product.domain.port.ProductDescriptionGenerator
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

/**
 * 테스트 환경 전용 Gemini 설정
 *
 * GeminiProperties와 ProductDescriptionGenerator를 Mock으로 제공합니다.
 */
@TestConfiguration
class TestGeminiConfig {
    @Bean
    @Primary
    fun geminiProperties(): GeminiProperties =
        GeminiProperties(
            apiKey = "test-api-key",
            model = "gemini-pro",
            maxTokens = 2000,
        )

    @Bean
    @Primary
    fun productDescriptionGenerator(): ProductDescriptionGenerator =
        object : ProductDescriptionGenerator {
            override fun generate(prompt: String): String = "테스트용 상품 설명: $prompt"
        }
}

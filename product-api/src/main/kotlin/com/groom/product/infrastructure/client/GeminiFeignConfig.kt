package com.groom.product.infrastructure.client

import com.groom.product.common.configuration.GeminiProperties
import feign.RequestInterceptor
import org.springframework.context.annotation.Bean

/**
 * Gemini Feign Client 설정.
 *
 * API 키를 헤더에 추가하는 RequestInterceptor를 설정합니다.
 */
class GeminiFeignConfig(
    private val geminiProperties: GeminiProperties,
) {
    /**
     * 모든 Gemini API 요청에 x-goog-api-key 헤더를 추가하는 인터셉터.
     */
    @Bean
    fun geminiRequestInterceptor(): RequestInterceptor =
        RequestInterceptor { template ->
            template.header("x-goog-api-key", geminiProperties.apiKey)
            template.header("Content-Type", "application/json")
        }
}

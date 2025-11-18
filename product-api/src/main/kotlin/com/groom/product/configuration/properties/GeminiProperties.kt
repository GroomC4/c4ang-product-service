package com.groom.product.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Google Gemini AI 설정 프로퍼티.
 */
@ConfigurationProperties(prefix = "gemini")
data class GeminiProperties(
    val apiKey: String,
    val model: String = "gemini-pro",
    val maxTokens: Int = 1024,
)

package com.groom.product.configuration.properties

import com.groom.product.adapter.outbound.ai.GeminiProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Configuration Properties 활성화.
 */
@Configuration
@EnableConfigurationProperties(
    GeminiProperties::class,
    S3Properties::class,
)
class PropertiesConfig

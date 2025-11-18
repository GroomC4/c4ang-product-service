package com.groom.product.configuration.properties

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

package com.groom.product.common.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * AWS S3 설정 프로퍼티.
 */
@ConfigurationProperties(prefix = "aws.s3")
data class S3Properties(
    val bucketName: String,
    val region: String,
)

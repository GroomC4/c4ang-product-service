package com.groom.product.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * AWS S3 설정 프로퍼티.
 *
 * enabled=false 일 경우 S3 관련 빈들이 비활성화됩니다.
 * k3d/dev 환경에서 AWS 자격증명이 없을 때 사용합니다.
 */
@ConfigurationProperties(prefix = "aws.s3")
data class S3Properties(
    val enabled: Boolean = false,
    val bucketName: String = "",
    val region: String = "ap-northeast-2",
)

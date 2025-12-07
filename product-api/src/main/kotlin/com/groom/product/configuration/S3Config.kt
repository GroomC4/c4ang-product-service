package com.groom.product.configuration

import com.groom.product.configuration.properties.S3Properties
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client

/**
 * AWS S3 설정.
 *
 * aws.s3.enabled=true 일 때만 S3Client Bean이 생성됩니다.
 * k3d/dev 환경에서 AWS 자격증명이 없을 경우 비활성화할 수 있습니다.
 */
@Configuration
@ConditionalOnProperty(prefix = "aws.s3", name = ["enabled"], havingValue = "true", matchIfMissing = false)
class S3Config(
    private val s3Properties: S3Properties,
) {
    @Bean
    fun s3Client(): S3Client =
        S3Client
            .builder()
            .region(Region.of(s3Properties.region))
            .credentialsProvider(DefaultCredentialsProvider.create())
            .build()
}

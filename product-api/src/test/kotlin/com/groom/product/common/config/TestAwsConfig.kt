package com.groom.product.common.config

import org.mockito.Mockito
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import software.amazon.awssdk.services.s3.S3Client

/**
 * 테스트 환경 전용 AWS 설정
 *
 * S3Client를 Mock으로 제공합니다.
 */
@TestConfiguration
class TestAwsConfig {
    @Bean
    @Primary
    fun s3Client(): S3Client = Mockito.mock(S3Client::class.java)
}

package com.groom.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.retry.annotation.EnableRetry
import org.springframework.scheduling.annotation.EnableAsync

/**
 * Product API Application.
 *
 * Spring Boot 기반의 상품 마이크로서비스입니다.
 * - Hexagonal Architecture (Ports and Adapters) 패턴 적용
 * - DDD (Domain-Driven Design) 패턴 적용
 * - Istio Service Mesh 기반 인증/인가
 * - Platform Core의 커스텀 DataSource 사용
 */
@SpringBootApplication(
    exclude = [DataSourceAutoConfiguration::class],
)
@EnableKafka
@EnableFeignClients
@EnableAsync
@EnableRetry
@ConfigurationPropertiesScan
class ProductApiApplication

fun main(args: Array<String>) {
    runApplication<ProductApiApplication>(*args)
}

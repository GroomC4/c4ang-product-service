package com.groom.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.kafka.annotation.EnableKafka

/**
 * Product API Application.
 *
 * Spring Boot 기반의 상품 마이크로서비스입니다.
 * - Hexagonal Architecture (Ports and Adapters) 패턴 적용
 * - DDD (Domain-Driven Design) 패턴 적용
 * - Istio Service Mesh 기반 인증/인가
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableKafka
@EnableFeignClients
@ConfigurationPropertiesScan
class ProductApiApplication

fun main(args: Array<String>) {
    runApplication<ProductApiApplication>(*args)
}

package com.groom.product.configuration.jpa

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * JPA Repository 설정
 *
 * Platform Core의 datasource-starter가 DataSource와 TransactionManager를 자동으로 구성합니다.
 */
@Configuration
@EnableJpaRepositories(
    basePackages = ["com.groom.product"],
)
class JpaConfig

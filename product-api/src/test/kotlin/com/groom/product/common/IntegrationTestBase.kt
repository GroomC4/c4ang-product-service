package com.groom.product.common

import com.groom.platform.testcontainers.annotation.IntegrationTest
import org.springframework.boot.test.context.SpringBootTest

/**
 * 통합 테스트 베이스 클래스
 *
 * **사용법:**
 * 1. 통합 테스트 클래스에서 이 클래스를 상속받기
 * 2. @SpringBootTest 어노테이션 제거 (중복 방지)
 * 3. 끝! Testcontainers 자동 시작됨
 *
 * **예시:**
 * ```kotlin
 * class ProductServiceIntegrationTest : IntegrationTestBase() {
 *     @Autowired
 *     private lateinit var productRepository: ProductRepository
 *
 *     @Test
 *     fun `테스트`() { ... }
 * }
 * ```
 *
 * **platform-core testcontainers-starter 사용:**
 * - @IntegrationTest 어노테이션 중앙화
 * - PostgreSQL Primary/Replica, Redis, Kafka 자동 시작
 * - 동적 포트 주입 자동화
 */
@IntegrationTest
@SpringBootTest(
    properties = [
        // Spring Profile
        "spring.profiles.active=test",

        // PostgreSQL
        "testcontainers.postgres.enabled=true",
        "testcontainers.postgres.replica-enabled=true",
        "testcontainers.postgres.schema-location=project:sql/schema.sql",

        // Redis
        "testcontainers.redis.enabled=true",

        // Kafka
        "testcontainers.kafka.enabled=true",
        "testcontainers.kafka.auto-create-topics=true",

        // Kafka Topics
        "testcontainers.kafka.topics[0].name=product.registered",
        "testcontainers.kafka.topics[0].partitions=3",
        "testcontainers.kafka.topics[0].replication-factor=1",

        "testcontainers.kafka.topics[1].name=product.deleted",
        "testcontainers.kafka.topics[1].partitions=1",
        "testcontainers.kafka.topics[1].replication-factor=1",
    ],
)
abstract class IntegrationTestBase

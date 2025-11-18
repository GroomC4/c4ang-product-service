package com.groom.product.common

import com.groom.platform.testcontainers.annotation.IntegrationTest
import org.springframework.boot.test.context.SpringBootTest

/**
 * ⚠️ 통합 테스트 베이스 클래스
 *
 * 모든 통합 테스트는 이 클래스를 상속해야 합니다.
 *
 * @IntegrationTest: Kafka/Schema Registry 동적 포트 자동 주입
 * @SpringBootTest properties: Testcontainers 설정
 *
 * 제공 기능:
 * - PostgreSQL Primary/Replica Testcontainers 자동 시작
 * - Redis Testcontainers 자동 시작
 * - Kafka Testcontainers 자동 시작
 * - Schema Registry Testcontainers 자동 시작
 * - 스키마 파일 자동 로딩 (product-api/sql/schema.sql)
 * - Primary-Replica 자동 라우팅
 *   - @Transactional(readOnly = false) → Primary DB
 *   - @Transactional(readOnly = true) → Replica DB
 */
@IntegrationTest
@SpringBootTest(
    properties = [
        // PostgreSQL Primary/Replica 활성화
        "testcontainers.postgres.enabled=true",
        "testcontainers.postgres.replica-enabled=true",

        // 스키마 파일 위치 (project: 접두사로 프로젝트 루트 기준 경로)
        // IntelliJ와 Gradle 모두 지원
        "testcontainers.postgres.schema-location=project:product-api/sql/schema.sql",

        // Redis 활성화
        "testcontainers.redis.enabled=true",

        // Kafka 활성화
        "testcontainers.kafka.enabled=true",

        // Schema Registry 활성화 (Avro 직렬화 지원)
        "testcontainers.schema-registry.enabled=true",
    ],
)
abstract class IntegrationTestBase

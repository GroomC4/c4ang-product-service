package com.groom.product.common

import com.groom.platform.testcontainers.annotation.IntegrationTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

/**
 * 통합 테스트 베이스 클래스
 *
 * 모든 통합 테스트는 이 클래스를 상속해야 합니다.
 *
 * Platform Core 1.2.5의 testcontainers-starter가 제공하는 기능:
 * - application-test.yml의 testcontainers 설정을 자동으로 읽어서 컨테이너 시작
 * - PostgreSQL Primary/Replica 자동 구성 및 라우팅
 * - Redis, Kafka, Schema Registry 자동 시작
 * - DataSource 자동 구성
 *
 * @IntegrationTest: Platform Core가 제공하는 통합 테스트 어노테이션
 * @ActiveProfiles("test"): application-test.yml 활성화
 */
@IntegrationTest
@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationTestBase

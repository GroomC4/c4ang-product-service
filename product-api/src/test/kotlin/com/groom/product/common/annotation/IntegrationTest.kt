package com.groom.product.common.annotation

import org.junit.jupiter.api.Tag

/**
 * Product Service 통합 테스트용 어노테이션
 *
 * platform-core의 testcontainers-starter를 사용하여 통합 테스트를 수행합니다.
 * IntegrationTestBase를 상속받아 사용하세요.
 *
 * 사용 예시:
 * ```kotlin
 * class ProductControllerIntegrationTest : IntegrationTestBase() {
 *     @Test
 *     fun `통합 테스트`() {
 *         // 테스트 로직
 *     }
 * }
 * ```
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Tag("integration-test")
annotation class IntegrationTest

package com.groom.product.common.annotation

/**
 * Product Service 통합 테스트용 어노테이션
 *
 * Platform Core의 testcontainers-starter가 자동으로 컨테이너를 구성합니다.
 * application-test.yml에 설정된 testcontainers 구성을 사용합니다.
 *
 * 사용 예시:
 * ```kotlin
 * @IntegrationTest
 * @SpringBootTest
 * @AutoConfigureMockMvc
 * class ProductControllerIntegrationTest {
 *     @Test
 *     fun `통합 테스트`() {
 *         // 테스트 로직
 *     }
 * }
 * ```
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class IntegrationTest

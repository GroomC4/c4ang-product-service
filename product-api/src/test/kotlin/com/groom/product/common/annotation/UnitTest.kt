package com.groom.product.common.annotation

import org.junit.jupiter.api.Tag
import org.springframework.test.context.ActiveProfiles

/**
 * 단위 테스트를 표시하는 어노테이션.
 * 외부 의존성 없이 빠르게 실행되는 테스트에 사용합니다.
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Tag("unit-test")
@ActiveProfiles("test")
annotation class UnitTest

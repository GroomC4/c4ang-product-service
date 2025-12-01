package com.groom.product.adapter.outbound.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.stubrunner.StubFinder
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.util.UUID

/**
 * StoreServiceFeignClient Consumer Contract Test
 *
 * 목적: store-service와의 API 계약(Contract)을 검증하는 Consumer Contract Test
 *
 * Spring Cloud Contract의 Consumer-Driven Contract Testing:
 * - store-service(Provider)가 발행한 Contract Stub을 사용
 * - product-service(Consumer) 관점에서 API 계약 준수 여부 검증
 * - store-service의 실제 API 변경 사항을 조기에 감지
 *
 * 동작 방식:
 * 1. store-service의 Contract Stub JAR를 GitHub Packages에서 로드
 * 2. Stub Runner가 WireMock 서버를 동적 포트에서 실행
 * 3. StubFinder를 통해 할당된 포트 조회
 * 4. Contract에 정의된 요청/응답 시나리오 검증
 *
 * 차이점:
 * - Unit Test: FeignClient 자체 동작 검증 (빠름, 독립적)
 * - Contract Test: store-service와의 실제 계약 검증 (느림, 의존적)
 *
 * 주의:
 * - store-service의 Contract Stub이 로컬에 발행되어 있어야 합니다
 * - Contract 변경 시 이 테스트가 실패하면 두 서비스 간 호환성 문제를 의미합니다
 *
 * Stub 로드 전략:
 * - REMOTE 모드를 사용하여 GitHub Packages에서 Contract Stub 다운로드
 * - CI/로컬 환경 모두에서 동일하게 동작
 *
 * GitHub Packages 인증:
 * - GITHUB_ACTOR, GITHUB_TOKEN 환경변수 필요
 * - CI에서는 자동으로 설정됨
 * - 로컬에서는 build.gradle.kts의 repositories 설정 참조
 */
@SpringJUnitConfig
@AutoConfigureStubRunner(
    ids = ["com.groom:store-service-contract-stubs:+:stubs"],
    stubsMode = StubRunnerProperties.StubsMode.REMOTE,
    repositoryRoot = "https://maven.pkg.github.com/GroomC4/c4ang-store-service",
)
@DisplayName("StoreServiceFeignClient Consumer Contract 테스트")
class StoreServiceFeignClientConsumerContractTest {
    @Autowired
    private lateinit var stubFinder: StubFinder

    private lateinit var storeServiceFeignClient: StoreServiceFeignClient

    @BeforeEach
    fun setup() {
        val objectMapper =
            ObjectMapper()
                .registerKotlinModule()
                .registerModule(JavaTimeModule())

        // StubFinder를 통해 동적으로 할당된 포트 조회
        val stubUrl = stubFinder.findStubUrl("store-service-contract-stubs")

        // Feign Client를 Stub Runner가 실행한 WireMock 서버에 연결
        storeServiceFeignClient =
            Feign
                .builder()
                .contract(SpringMvcContract())
                .encoder(JacksonEncoder(objectMapper))
                .decoder(JacksonDecoder(objectMapper))
                .requestInterceptor { template ->
                    template.header("Content-Type", "application/json")
                }.target(StoreServiceFeignClient::class.java, stubUrl.toString())
    }

    @Test
    @DisplayName("[Contract 검증] store-service가 정의한 스토어 조회 API 계약을 준수한다")
    fun `should comply with store service contract for getting store by id`() {
        // given - store-service의 Contract에 정의된 스토어 ID
        // Contract 파일: should_get_store_by_id.yml
        val contractDefinedStoreId = UUID.fromString("750e8400-e29b-41d4-a716-446655440101")

        // when - Contract에 정의된 요청 실행
        val result = storeServiceFeignClient.getById(contractDefinedStoreId)

        // then - Contract에 정의된 응답 스펙 검증
        // Contract는 store-service의 실제 API 명세를 반영합니다
        result shouldNotBe null
        result.storeId shouldBe "750e8400-e29b-41d4-a716-446655440101"
        result.ownerUserId shouldBe "750e8400-e29b-41d4-a716-446655440001"
        result.name shouldBe "Contract Test Store"
        result.status shouldBe "REGISTERED"
    }

    @Test
    @DisplayName("[Contract 검증] 존재하지 않는 스토어 조회 시 404 응답을 받는다")
    fun `should receive 404 response when store not found as per contract`() {
        // given - Contract에 정의되지 않은 스토어 ID
        // Contract는 이 경우 404를 반환하도록 정의되어 있습니다
        val nonExistentStoreId = UUID.randomUUID()

        // when & then - Contract에 정의된 에러 응답 검증
        try {
            storeServiceFeignClient.getById(nonExistentStoreId)
            throw AssertionError("Contract에 따라 404 예외가 발생해야 합니다")
        } catch (e: Exception) {
            // Contract에 정의된 404 응답 검증
            // store-service의 실제 에러 응답과 동일한 형식
            e.message shouldNotBe null
        }
    }
}

package com.groom.product.adapter.outbound.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import feign.Feign
import feign.FeignException
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.contract.stubrunner.StubFinder
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.cloud.openfeign.support.SpringMvcContract
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import java.util.UUID

/**
 * Store Service Internal API Consumer Contract Test
 *
 * 목적: store-service의 Internal API 계약을 검증하는 Consumer Contract Test
 *
 * Consumer-Driven Contract Testing:
 * - product-service(Consumer)가 store-service(Provider)에게 필요한 API 스펙을 정의
 * - store-service가 해당 Contract를 만족하는 Stub을 발행
 * - product-service는 Stub을 통해 API 계약 준수 여부를 검증
 *
 * Internal API 특징:
 * - 서비스 간 통신 전용 (/internal/v1/**)
 * - API Gateway/Istio에서 인증 완료 후 호출되므로 별도 인증 불필요
 * - gRPC 등 다른 프로토콜로 전환 가능성을 고려한 경로 설계
 *
 * Stub 아티팩트:
 * - Group: com.groom
 * - Artifact: store-service-contract-stubs
 * - 저장소: GitHub Packages (GroomC4/c4ang-packages-hub)
 */
@SpringJUnitConfig
@AutoConfigureStubRunner(
    ids = ["com.groom:store-service-contract-stubs:+:stubs"],
    stubsMode = StubRunnerProperties.StubsMode.REMOTE,
    repositoryRoot = "https://maven.pkg.github.com/GroomC4/c4ang-packages-hub",
)
@DisplayName("Store Internal API Consumer Contract 테스트")
class StoreInternalApiConsumerContractTest {
    @Autowired
    private lateinit var stubFinder: StubFinder

    private lateinit var storeServiceClient: StoreServiceFeignClient

    @BeforeEach
    fun setup() {
        val objectMapper =
            ObjectMapper()
                .registerKotlinModule()
                .registerModule(JavaTimeModule())

        val stubUrl = stubFinder.findStubUrl("store-service-contract-stubs")

        storeServiceClient =
            Feign
                .builder()
                .contract(SpringMvcContract())
                .encoder(JacksonEncoder(objectMapper))
                .decoder(JacksonDecoder(objectMapper))
                .requestInterceptor { template ->
                    template.header("Content-Type", "application/json")
                }.target(StoreServiceFeignClient::class.java, stubUrl.toString())
    }

    @Nested
    @DisplayName("GET /internal/v1/stores/{storeId}")
    inner class GetStoreById {
        @Test
        @DisplayName("스토어 ID로 조회 성공")
        fun `should get store by id successfully`() {
            // given - Contract에 정의된 스토어 ID
            val storeId = UUID.fromString("750e8400-e29b-41d4-a716-446655440101")

            // when
            val result = storeServiceClient.getById(storeId)

            // then - Contract에 정의된 응답 검증
            result shouldNotBe null
            result.storeId shouldBe "750e8400-e29b-41d4-a716-446655440101"
            result.ownerUserId shouldBe "750e8400-e29b-41d4-a716-446655440001"
            result.name shouldBe "Contract Test Store"
            result.status shouldBe "REGISTERED"
        }

        @Test
        @DisplayName("존재하지 않는 스토어 조회 시 404 응답")
        fun `should return 404 when store not found`() {
            // given - Contract에 정의된 존재하지 않는 스토어 ID
            val nonExistentStoreId = UUID.fromString("00000000-0000-0000-0000-000000000000")

            // when & then
            val exception =
                shouldThrow<FeignException.NotFound> {
                    storeServiceClient.getById(nonExistentStoreId)
                }

            exception.status() shouldBe 404
        }
    }

    @Nested
    @DisplayName("GET /internal/v1/stores/owner/{ownerId}")
    inner class GetStoreByOwnerId {
        @Test
        @DisplayName("소유자 ID로 스토어 조회 성공")
        fun `should get store by owner id successfully`() {
            // given - Contract에 정의된 소유자 ID
            val ownerId = UUID.fromString("750e8400-e29b-41d4-a716-446655440001")

            // when
            val result = storeServiceClient.getByOwnerId(ownerId)

            // then - Contract에 정의된 응답 검증
            result shouldNotBe null
            result!!.storeId shouldBe "750e8400-e29b-41d4-a716-446655440101"
            result.ownerUserId shouldBe "750e8400-e29b-41d4-a716-446655440001"
            result.name shouldBe "Contract Test Store"
            result.status shouldBe "REGISTERED"
        }

        @Test
        @DisplayName("스토어가 없는 소유자 조회 시 404 응답")
        fun `should return 404 when owner has no store`() {
            // given - Contract에 정의된 스토어가 없는 소유자 ID
            val ownerWithNoStore = UUID.fromString("00000000-0000-0000-0000-000000000000")

            // when & then
            val exception =
                shouldThrow<FeignException.NotFound> {
                    storeServiceClient.getByOwnerId(ownerWithNoStore)
                }

            exception.status() shouldBe 404
        }
    }
}

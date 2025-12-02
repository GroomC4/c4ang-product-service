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
 * store-service의 Internal API 계약을 검증하는 Consumer Contract Test
 *
 * 이 테스트는 store-service가 contract stub을 발행한 후에만 실행됩니다.
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
            val storeId = UUID.fromString("750e8400-e29b-41d4-a716-446655440101")

            val result = storeServiceClient.getById(storeId)

            result shouldNotBe null
            result.storeId shouldBe "750e8400-e29b-41d4-a716-446655440101"
            result.ownerUserId shouldBe "750e8400-e29b-41d4-a716-446655440001"
            result.name shouldBe "Contract Test Store"
            result.status shouldBe "REGISTERED"
        }

        @Test
        @DisplayName("존재하지 않는 스토어 조회 시 404 응답")
        fun `should return 404 when store not found`() {
            val nonExistentStoreId = UUID.fromString("00000000-0000-0000-0000-000000000000")

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
            val ownerId = UUID.fromString("750e8400-e29b-41d4-a716-446655440001")

            val result = storeServiceClient.getByOwnerId(ownerId)

            result shouldNotBe null
            result!!.storeId shouldBe "750e8400-e29b-41d4-a716-446655440101"
            result.ownerUserId shouldBe "750e8400-e29b-41d4-a716-446655440001"
            result.name shouldBe "Contract Test Store"
            result.status shouldBe "REGISTERED"
        }

        @Test
        @DisplayName("스토어가 없는 소유자 조회 시 404 응답")
        fun `should return 404 when owner has no store`() {
            val ownerWithNoStore = UUID.fromString("00000000-0000-0000-0000-000000000000")

            val exception =
                shouldThrow<FeignException.NotFound> {
                    storeServiceClient.getByOwnerId(ownerWithNoStore)
                }

            exception.status() shouldBe 404
        }
    }
}

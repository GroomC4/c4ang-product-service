package com.groom.product.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching
import com.groom.product.adapter.outbound.client.dto.StoreInternalDto
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.time.LocalDateTime
import java.util.UUID

/**
 * WireMock Store Service Stubbing 유틸리티
 *
 * 테스트에서 Store Service API 응답을 쉽게 stub할 수 있는 확장 함수들을 제공합니다.
 */
object WireMockStoreStubbing {
    // 테스트용 기본 스토어 데이터
    val TEST_STORE_ID: UUID = UUID.fromString("750e8400-e29b-41d4-a716-446655440101")
    val TEST_OWNER_ID: UUID = UUID.fromString("750e8400-e29b-41d4-a716-446655440001")

    private val objectMapper = ObjectMapper().apply {
        findAndRegisterModules()
    }

    /**
     * 스토어 조회 성공 응답 stub
     */
    fun WireMockServer.stubStoreGetById(
        storeId: UUID,
        store: StoreInternalDto,
    ) {
        this.stubFor(
            get(urlPathMatching("/internal/v1/stores/$storeId"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(store))
                )
        )
    }

    /**
     * 스토어 조회 404 응답 stub
     */
    fun WireMockServer.stubStoreNotFound(storeId: UUID) {
        this.stubFor(
            get(urlPathMatching("/internal/v1/stores/$storeId"))
                .willReturn(
                    aResponse()
                        .withStatus(404)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody("""{"error": "Store not found", "storeId": "$storeId"}""")
                )
        )
    }

    /**
     * 테스트용 스토어 DTO 생성
     */
    fun createTestStore(
        storeId: UUID = TEST_STORE_ID,
        ownerUserId: UUID = TEST_OWNER_ID,
        name: String = "Test Store",
        description: String? = "A test store for testing",
        status: String = "REGISTERED",
        averageRating: Double? = 4.5,
        reviewCount: Int? = 10,
    ): StoreInternalDto = StoreInternalDto(
        storeId = storeId.toString(),
        ownerUserId = ownerUserId.toString(),
        name = name,
        description = description,
        status = status,
        averageRating = averageRating,
        reviewCount = reviewCount,
        launchedAt = LocalDateTime.now().minusDays(30),
        createdAt = LocalDateTime.now().minusDays(60),
        updatedAt = LocalDateTime.now(),
    )

    /**
     * 기본 스토어 stub 설정
     */
    fun WireMockServer.setupDefaultStoreStubs() {
        // 테스트용 기본 스토어
        stubStoreGetById(TEST_STORE_ID, createTestStore())

        // 존재하지 않는 스토어
        stubStoreNotFound(UUID.fromString("00000000-0000-0000-0000-000000000000"))
    }
}

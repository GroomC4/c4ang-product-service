package com.groom.product.adapter.outbound.client

import com.groom.product.adapter.outbound.client.dto.StoreInternalDto
import feign.FeignException
import feign.Request
import feign.RequestTemplate
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.UUID

/**
 * 테스트 환경에서 StoreServiceClient를 대체하는 Mock Client
 *
 * 통합 테스트에서 실제 Store Service에 의존하지 않고 테스트할 수 있도록 합니다.
 */
@Component
@Profile("test")
@Primary
class MockStoreServiceClient : StoreServiceClient {
    companion object {
        // 테스트용 스토어 데이터
        val TEST_STORE_ID = UUID.fromString("750e8400-e29b-41d4-a716-446655440101")
        val TEST_OWNER_ID = UUID.fromString("750e8400-e29b-41d4-a716-446655440001")

        private val testStores =
            mutableMapOf(
                TEST_STORE_ID to
                    StoreInternalDto(
                        storeId = TEST_STORE_ID.toString(),
                        ownerUserId = TEST_OWNER_ID.toString(),
                        name = "Test Store",
                        description = "A test store for testing",
                        status = "REGISTERED",
                        averageRating = 4.5,
                        reviewCount = 10,
                        launchedAt = LocalDateTime.now().minusDays(30),
                        createdAt = LocalDateTime.now().minusDays(60),
                        updatedAt = LocalDateTime.now(),
                    ),
            )

        fun addTestStore(store: StoreInternalDto) {
            testStores[UUID.fromString(store.storeId)] = store
        }

        fun clearTestStores() {
            testStores.clear()
            // 기본 테스트 스토어 다시 추가
            testStores[TEST_STORE_ID] =
                StoreInternalDto(
                    storeId = TEST_STORE_ID.toString(),
                    ownerUserId = TEST_OWNER_ID.toString(),
                    name = "Test Store",
                    description = "A test store for testing",
                    status = "REGISTERED",
                    averageRating = 4.5,
                    reviewCount = 10,
                    launchedAt = LocalDateTime.now().minusDays(30),
                    createdAt = LocalDateTime.now().minusDays(60),
                    updatedAt = LocalDateTime.now(),
                )
        }
    }

    override fun getById(storeId: UUID): StoreInternalDto =
        testStores[storeId]
            ?: throw FeignException.NotFound(
                "Store not found: $storeId",
                Request.create(
                    Request.HttpMethod.GET,
                    "/internal/v1/stores/$storeId",
                    emptyMap(),
                    null,
                    RequestTemplate(),
                ),
                null,
                emptyMap(),
            )
}

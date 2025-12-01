package com.groom.product.adapter.outbound.client

import com.groom.product.adapter.outbound.client.dto.StoreInternalDto
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

/**
 * store 서비스와 통신하기 위한 Feign Client
 *
 * Store Service의 API와 통신합니다.
 *
 * 테스트 환경에서는 MockStoreServiceClient가 사용됩니다.
 */
@FeignClient(
    name = "store-service",
    url = "\${feign.clients.store-service.url:http://localhost:8082}",
)
@Profile("!test")
@ConditionalOnMissingBean(StoreServiceClient::class)
interface StoreServiceFeignClient : StoreServiceClient {
    /**
     * 특정 스토어 정보 조회
     *
     * Store Service의 API를 호출합니다.
     * 엔드포인트: GET /api/v1/stores/{storeId}
     *
     * @param storeId 스토어 ID
     * @return StoreInternalDto - 스토어 정보
     */
    @GetMapping("/api/v1/stores/{storeId}")
    override fun getById(
        @PathVariable storeId: UUID,
    ): StoreInternalDto

    /**
     * 소유자의 스토어 정보 조회
     *
     * Store Service의 API를 호출합니다.
     * 엔드포인트: GET /api/v1/stores/owner/{ownerId}
     *
     * @param ownerId 소유자 ID
     * @return StoreInternalDto - 스토어 정보
     */
    @GetMapping("/api/v1/stores/owner/{ownerId}")
    override fun getByOwnerId(
        @PathVariable ownerId: UUID,
    ): StoreInternalDto?
}

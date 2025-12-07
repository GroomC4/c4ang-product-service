package com.groom.product.adapter.outbound.client

import com.groom.product.adapter.outbound.client.dto.StoreInternalDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID

/**
 * store 서비스와 통신하기 위한 Feign Client
 *
 * Store Service의 Internal API와 통신합니다.
 * Internal API는 서비스 간 통신을 위해 설계된 API입니다.
 *
 * 테스트 환경에서는 WireMock을 통해 stub 응답을 받습니다.
 */
@FeignClient(
    name = "store-service",
    url = "\${feign.clients.store-service.url:http://localhost:8082}",
)
interface StoreServiceFeignClient : StoreServiceClient {
    /**
     * 특정 스토어 정보 조회
     *
     * Store Service의 Internal API를 호출합니다.
     * 엔드포인트: GET /internal/v1/stores/{storeId}
     *
     * @param storeId 스토어 ID
     * @return StoreInternalDto - 스토어 정보
     */
    @GetMapping("/internal/v1/stores/{storeId}")
    override fun getById(
        @PathVariable storeId: UUID,
    ): StoreInternalDto
}

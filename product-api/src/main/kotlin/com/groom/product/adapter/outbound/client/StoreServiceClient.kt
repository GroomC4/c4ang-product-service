package com.groom.product.adapter.outbound.client

import com.groom.product.adapter.outbound.client.dto.StoreInternalDto
import java.util.UUID

/**
 * store 서비스와 통신하기 위한 추상 인터페이스
 *
 * 구현체:
 * - StoreServiceFeignClient: REST API 통신 (현재)
 * - StoreServiceGrpcClient: gRPC 통신 (향후 추가 가능)
 *
 * 이 인터페이스를 통해 통신 방식을 추상화하여,
 * 향후 REST에서 gRPC로 변경 시 Adapter 코드 수정 없이 전환 가능합니다.
 */
interface StoreServiceClient {
    /**
     * 특정 스토어 정보 조회
     *
     * @param storeId 스토어 ID
     * @return StoreInternalDto - 스토어 정보
     */
    fun getById(storeId: UUID): StoreInternalDto
}

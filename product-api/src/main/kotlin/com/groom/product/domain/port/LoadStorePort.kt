package com.groom.product.domain.port

import com.groom.product.domain.model.StoreInfo
import java.util.UUID

/**
 * 스토어 정보 조회를 위한 Outbound Port.
 *
 * Product 도메인이 Store 도메인의 정보가 필요할 때 사용하는 포트입니다.
 * 외부 서비스 호출 또는 이벤트 기반 데이터 동기화로 구현될 수 있습니다.
 */
interface LoadStorePort {
    /**
     * 스토어 ID와 소유자 ID로 스토어 정보를 조회합니다.
     *
     * @param storeId 스토어 ID
     * @param ownerId 소유자 ID
     * @return 스토어 정보 또는 null (존재하지 않거나 소유자가 일치하지 않을 경우)
     */
    fun loadByIdAndOwnerId(
        storeId: UUID,
        ownerId: UUID,
    ): StoreInfo?
}

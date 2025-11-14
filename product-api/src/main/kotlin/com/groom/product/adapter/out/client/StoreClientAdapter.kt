package com.groom.product.adapter.out.client

import com.groom.product.domain.model.StoreInfo
import com.groom.product.domain.port.LoadStorePort
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * Store 외부 서비스 호출 Adapter.
 *
 * LoadStorePort를 구현하여 Domain Layer와 외부 Store Service를 연결합니다.
 *
 * TODO: 실제 Feign Client 또는 RestTemplate을 사용하여 Store Service 호출 구현 필요
 * 현재는 임시로 기존 ProductStoreReaderAdapter의 구현을 delegate합니다.
 */
@Component
class StoreClientAdapter(
    // TODO: Inject Feign Client when Store Service is ready
    // private val storeClient: StoreClient
) : LoadStorePort {
    override fun loadByIdAndOwnerId(
        storeId: UUID,
        ownerId: UUID,
    ): StoreInfo? {
        // TODO: Implement actual external service call
        // Example:
        // try {
        //     val response = storeClient.getStore(storeId, ownerId)
        //     return StoreInfo(id = response.id, name = response.name, ownerId = response.ownerId)
        // } catch (e: FeignException) {
        //     return null
        // }

        // Temporary: Return null (will be implemented when Store Service is integrated)
        return null
    }
}

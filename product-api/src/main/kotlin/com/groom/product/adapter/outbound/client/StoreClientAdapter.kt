package com.groom.product.adapter.outbound.client

import com.groom.product.domain.model.StoreInfo
import com.groom.product.domain.port.LoadStorePort
import feign.FeignException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * Store 외부 서비스 호출 Adapter.
 *
 * LoadStorePort를 구현하여 Domain Layer와 외부 Store Service를 연결합니다.
 *
 * StoreServiceClient를 통해 Store Service와 통신합니다.
 * - 실제 환경: StoreServiceFeignClient (REST API)
 * - 테스트 환경: MockStoreServiceClient
 */
@Component
class StoreClientAdapter(
    private val storeServiceClient: StoreServiceClient,
) : LoadStorePort {
    override fun loadByIdAndOwnerId(
        storeId: UUID,
        ownerId: UUID,
    ): StoreInfo? {
        return try {
            val storeDto = storeServiceClient.getById(storeId)

            // 소유자 검증
            if (storeDto.ownerUserId != ownerId.toString()) {
                logger.warn { "Store owner mismatch: storeId=$storeId, expected=$ownerId, actual=${storeDto.ownerUserId}" }
                return null
            }

            StoreInfo(
                id = UUID.fromString(storeDto.storeId),
                name = storeDto.name,
            )
        } catch (e: FeignException.NotFound) {
            logger.debug { "Store not found: storeId=$storeId" }
            null
        } catch (e: Exception) {
            logger.error(e) { "Failed to load store: storeId=$storeId" }
            null
        }
    }
}

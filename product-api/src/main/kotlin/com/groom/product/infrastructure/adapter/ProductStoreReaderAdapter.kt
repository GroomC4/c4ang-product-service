package com.groom.product.infrastructure.adapter

import com.groom.product.domain.model.StoreInfo
import com.groom.product.domain.port.LoadStorePort
import com.groom.ecommerce.store.infrastructure.repository.StoreRepositoryImpl
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.UUID

@Component
class ProductStoreReaderAdapter(
    private val storeJpaRepository: StoreRepositoryImpl,
): ProductStoreReader {
    override fun findByIdAndOwnerId(
        storeId: UUID,
        requestUserId: UUID
    ): Optional<StoreInfo> {
        return storeJpaRepository.findById(storeId)
            .filter { it.ownerUserId == requestUserId }
            .map { store ->
                StoreInfo(
                    id = store.id,
                    name = store.name,
                )
            }
    }
}

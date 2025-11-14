package com.groom.product.domain.port

import com.groom.product.domain.model.StoreInfo
import java.util.Optional
import java.util.UUID

interface ProductStoreReader {
    fun findByIdAndOwnerId(storeId: UUID, requestUserId: UUID): Optional<StoreInfo>
}

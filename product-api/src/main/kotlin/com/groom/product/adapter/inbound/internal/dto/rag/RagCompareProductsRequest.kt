package com.groom.product.adapter.inbound.internal.dto.rag

import java.util.UUID

/**
 * RAG 서비스용 향수 비교 요청 DTO.
 *
 * @property productIds 비교할 향수 ID 목록 (2~4개)
 */
data class RagCompareProductsRequest(
    val productIds: List<UUID>,
) {
    init {
        require(productIds.size in 2..4) { "비교할 향수는 2~4개 사이여야 합니다." }
    }
}

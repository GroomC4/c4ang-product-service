package com.groom.product.adapter.inbound.internal.dto.rag

import java.util.UUID

/**
 * RAG 서비스용 유사 향수 검색 요청 DTO.
 *
 * @property productId 기준 향수 ID
 * @property topK 결과 개수 (기본값: 3)
 */
data class RagSimilarProductsRequest(
    val productId: UUID,
    val topK: Int = 3,
)

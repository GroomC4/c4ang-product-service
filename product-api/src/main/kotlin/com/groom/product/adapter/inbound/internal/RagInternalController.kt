package com.groom.product.adapter.inbound.internal

import com.groom.product.adapter.inbound.internal.dto.ProductNotFoundErrorResponse
import com.groom.product.adapter.inbound.internal.dto.rag.RagCompareProductsRequest
import com.groom.product.adapter.inbound.internal.dto.rag.RagCompareProductsResponse
import com.groom.product.adapter.inbound.internal.dto.rag.RagProductDetailResponse
import com.groom.product.adapter.inbound.internal.dto.rag.RagProductSearchRequest
import com.groom.product.adapter.inbound.internal.dto.rag.RagProductSearchResponse
import com.groom.product.adapter.inbound.internal.dto.rag.RagSimilarProductsRequest
import com.groom.product.adapter.inbound.internal.dto.rag.RagSimilarProductsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * RAG(챗봇) 서비스용 Internal API Controller.
 *
 * 챗봇이 향수 검색, 추천, 비교 등의 기능을 수행할 때 사용하는 Internal API입니다.
 * Consumer-Driven Contract 패턴에 따라 RAG 서비스가 필요로 하는 필드를 반환합니다.
 *
 * Endpoints:
 * - POST /internal/v1/rag/products/search - 향수 검색/추천
 * - GET /internal/v1/rag/products/{id} - 향수 상세 조회
 * - POST /internal/v1/rag/products/similar - 유사 향수 검색
 * - POST /internal/v1/rag/products/compare - 향수 비교
 */
@Tag(name = "RAG Internal API", description = "Internal API for RAG (chatbot) service")
@RestController
@RequestMapping("/internal/v1/rag/products")
class RagInternalController {
    /**
     * 향수 검색/추천
     *
     * 자연어 쿼리와 필터를 기반으로 향수를 검색합니다.
     *
     * @param request 검색 요청 DTO
     * @return 검색 결과 목록
     */
    @Operation(summary = "향수 검색/추천", description = "자연어 쿼리와 필터를 기반으로 향수를 검색합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "검색 성공",
                content = [Content(schema = Schema(implementation = RagProductSearchResponse::class))],
            ),
        ],
    )
    @PostMapping("/search", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun searchProducts(
        @RequestBody request: RagProductSearchRequest,
    ): RagProductSearchResponse {
        // TODO: 실제 구현 필요
        throw UnsupportedOperationException("Not implemented yet")
    }

    /**
     * 향수 상세 조회
     *
     * 특정 향수의 상세 정보(노트, 설명 등)를 조회합니다.
     *
     * @param id 향수 고유 ID
     * @return 향수 상세 정보
     * @throws com.groom.product.common.exception.ProductException.ProductNotFound 향수를 찾을 수 없는 경우
     */
    @Operation(summary = "향수 상세 조회", description = "특정 향수의 상세 정보(노트, 설명 등)를 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                content = [Content(schema = Schema(implementation = RagProductDetailResponse::class))],
            ),
            ApiResponse(
                responseCode = "404",
                description = "향수를 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ProductNotFoundErrorResponse::class))],
            ),
        ],
    )
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProductDetail(
        @PathVariable id: UUID,
    ): RagProductDetailResponse {
        // TODO: 실제 구현 필요
        throw UnsupportedOperationException("Not implemented yet")
    }

    /**
     * 유사 향수 검색
     *
     * 특정 향수와 비슷한 향수를 찾습니다. (KNN 유사도 탐색)
     *
     * @param request 유사 향수 검색 요청 DTO
     * @return 유사 향수 목록
     * @throws com.groom.product.common.exception.ProductException.ProductNotFound 기준 향수를 찾을 수 없는 경우
     */
    @Operation(summary = "유사 향수 검색", description = "특정 향수와 비슷한 향수를 찾습니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "검색 성공",
                content = [Content(schema = Schema(implementation = RagSimilarProductsResponse::class))],
            ),
            ApiResponse(
                responseCode = "404",
                description = "기준 향수를 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ProductNotFoundErrorResponse::class))],
            ),
        ],
    )
    @PostMapping("/similar", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun findSimilarProducts(
        @RequestBody request: RagSimilarProductsRequest,
    ): RagSimilarProductsResponse {
        // TODO: 실제 구현 필요
        throw UnsupportedOperationException("Not implemented yet")
    }

    /**
     * 향수 비교
     *
     * 2~4개의 향수를 비교 분석합니다.
     *
     * @param request 향수 비교 요청 DTO
     * @return 비교 결과
     */
    @Operation(summary = "향수 비교", description = "2~4개의 향수를 비교 분석합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "비교 성공",
                content = [Content(schema = Schema(implementation = RagCompareProductsResponse::class))],
            ),
        ],
    )
    @PostMapping("/compare", produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun compareProducts(
        @RequestBody request: RagCompareProductsRequest,
    ): RagCompareProductsResponse {
        // TODO: 실제 구현 필요
        throw UnsupportedOperationException("Not implemented yet")
    }
}

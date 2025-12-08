package com.groom.product.adapter.inbound.internal

import com.groom.product.adapter.inbound.internal.dto.ProductNotFoundErrorResponse
import com.groom.product.adapter.inbound.internal.dto.rag.RagCompareProductsRequest
import com.groom.product.adapter.inbound.internal.dto.rag.RagCompareProductsResponse
import com.groom.product.adapter.inbound.internal.dto.rag.RagProductDetailResponse
import com.groom.product.adapter.inbound.internal.dto.rag.RagProductSearchRequest
import com.groom.product.adapter.inbound.internal.dto.rag.RagProductSearchResponse
import com.groom.product.application.service.RagPerfumeService
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
 * RAG(챗봇) 서비스용 Internal API Controller (v2).
 *
 * 챗봇이 향수 검색, 상세조회, 비교 등의 기능을 수행할 때 사용하는 Internal API입니다.
 * Consumer-Driven Contract 패턴에 따라 RAG 서비스가 필요로 하는 필드를 반환합니다.
 *
 * v2 변경사항:
 * - 용량 정보는 향수 상세정보에 포함 (sizes 배열)
 * - top_k, similarity_score 등 유사도 관련 파라미터 제거 (RAG에서 벡터DB로 처리)
 * - Product 서비스는 조건에 맞는 제품 정보만 제공
 * - 유사 향수 검색 API는 검색 API의 product_ids 파라미터로 통합
 *
 * Endpoints:
 * - POST /internal/v1/rag/perfumes/search - 향수 검색
 * - GET /internal/v1/rag/perfumes/{id} - 향수 상세 조회
 * - POST /internal/v1/rag/perfumes/compare - 향수 비교
 */
@Tag(name = "RAG Internal API", description = "Internal API for RAG (chatbot) service")
@RestController
@RequestMapping("/internal/v1/rag/perfumes")
class RagInternalController(
    private val ragPerfumeService: RagPerfumeService,
) {
    /**
     * 향수 검색.
     *
     * 조건에 맞는 향수 목록을 검색합니다.
     * product_ids가 제공되면 다른 필터는 무시하고 해당 ID의 향수만 반환합니다.
     * RAG에서 벡터DB로 유사 향수 ID를 찾은 후, 이 API로 상세 정보를 조회합니다.
     *
     * @param request 검색 요청 DTO
     * @return 검색 결과 목록
     */
    @Operation(summary = "향수 검색", description = "조건에 맞는 향수 목록을 검색합니다. product_ids가 제공되면 다른 필터는 무시됩니다.")
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
        val perfumes =
            ragPerfumeService.searchPerfumes(
                productIds = request.productIds,
                brand = request.brand,
                gender = request.gender,
            )
        return RagProductSearchResponse.from(perfumes)
    }

    /**
     * 향수 상세 조회.
     *
     * 특정 향수의 상세 정보(노트, 설명, 용량별 가격 등)를 조회합니다.
     *
     * @param id 향수 고유 ID
     * @return 향수 상세 정보
     * @throws com.groom.product.common.exception.ProductException.ProductNotFound 향수를 찾을 수 없는 경우
     */
    @Operation(summary = "향수 상세 조회", description = "특정 향수의 상세 정보(노트, 설명, 용량별 가격 등)를 조회합니다.")
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
        val perfume = ragPerfumeService.getPerfumeDetail(id)
        return RagProductDetailResponse.from(perfume)
    }

    /**
     * 향수 비교.
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
        val perfumes = ragPerfumeService.comparePerfumes(request.productIds)
        return RagCompareProductsResponse.from(perfumes)
    }
}

package com.groom.product.adapter.inbound.internal

import com.groom.product.adapter.inbound.internal.dto.InternalProductResponse
import com.groom.product.adapter.inbound.internal.dto.ProductNotFoundErrorResponse
import com.groom.product.application.dto.GetProductForOrderQuery
import com.groom.product.application.dto.GetProductsForOrderQuery
import com.groom.product.application.service.GetProductForOrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * Internal API Controller for Product Service.
 *
 * 다른 도메인 서비스(Order, Payment 등)가 사용하는 Internal API입니다.
 * Consumer-Driven Contract 패턴에 따라 Consumer가 필요로 하는 필드만 반환합니다.
 *
 * Endpoints:
 * - GET /internal/v1/products/{id} - 상품 단건 조회
 * - GET /internal/v1/products?ids=... - 상품 다건 조회
 */
@Tag(name = "Product Internal API", description = "Internal API for other domain services")
@RestController
@RequestMapping("/internal/v1/products")
class ProductInternalController(
    private val getProductForOrderService: GetProductForOrderService,
) {
    /**
     * 상품 ID로 상품 단건 조회
     *
     * @param id 상품 ID
     * @return 상품 정보 (id, storeId, name, storeName, price)
     * @throws ProductException.ProductNotFound 상품을 찾을 수 없는 경우
     */
    @Operation(summary = "상품 단건 조회", description = "상품 ID로 상품 정보를 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "조회 성공",
                content = [Content(schema = Schema(implementation = InternalProductResponse::class))],
            ),
            ApiResponse(
                responseCode = "404",
                description = "상품을 찾을 수 없음",
                content = [Content(schema = Schema(implementation = ProductNotFoundErrorResponse::class))],
            ),
        ],
    )
    @GetMapping("/{id}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProductById(
        @PathVariable id: UUID,
    ): InternalProductResponse {
        val query = GetProductForOrderQuery(productId = id)
        val result = getProductForOrderService.getProductById(query)
        return InternalProductResponse.from(result)
    }

    /**
     * 상품 ID 목록으로 상품 다건 조회
     *
     * @param ids 상품 ID 목록 (쉼표로 구분)
     * @return 상품 정보 목록 (존재하는 상품만 반환, 존재하지 않는 ID는 무시)
     */
    @Operation(summary = "상품 다건 조회", description = "상품 ID 목록으로 상품 정보를 조회합니다. 존재하지 않는 ID는 무시됩니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "조회 성공 (빈 목록 가능)",
            ),
        ],
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProductsByIds(
        @RequestParam ids: String,
    ): List<InternalProductResponse> {
        val productIds =
            ids
                .split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .mapNotNull { runCatching { UUID.fromString(it) }.getOrNull() }

        val query = GetProductsForOrderQuery(productIds = productIds)
        val results = getProductForOrderService.getProductsByIds(query)
        return results.map { InternalProductResponse.from(it) }
    }
}

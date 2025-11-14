package com.groom.product.presentation.web

import com.groom.product.application.dto.GetProductDetailQuery
import com.groom.product.application.dto.ListOwnerProductsQuery
import com.groom.product.application.dto.SearchProductsQuery
import com.groom.product.application.service.GetProductDetailService
import com.groom.product.application.service.ListOwnerProductsService
import com.groom.product.application.service.SearchProductsService
import com.groom.product.presentation.web.dto.GetProductDetailResponse
import com.groom.product.presentation.web.dto.ListOwnerProductsResponse
import com.groom.product.presentation.web.dto.SearchProductsResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

/**
 * 상품 조회(Query) Controller.
 *
 * 상품 정보를 조회하는 모든 작업을 처리합니다.
 *
 * 엔드포인트:
 * - GET /api/v1/products/owner - 판매자 상품 목록 조회 (판매자만)
 * - GET /api/v1/products/{id} - 상품 상세 조회
 * - GET /api/v1/products - 상품 목록 조회 (검색 및 필터링)
 */
@Tag(name = "Product Query", description = "상품 조회 API")
@RestController
@RequestMapping("/api/v1/products")
class ProductQueryController(
    private val listOwnerProductsService: ListOwnerProductsService,
    private val getProductDetailService: GetProductDetailService,
    private val searchProductsService: SearchProductsService,
) {
    /**
     * 판매자의 상품 목록을 조회합니다.
     *
     * 판매자(스토어 소유자)만 조회 가능합니다.
     * 숨김 처리된 상품도 포함하여 조회되며, 삭제된 상품은 옵션으로 포함 가능합니다.
     *
     * @param storeId 스토어 ID (필수)
     * @param page 페이지 번호 (0부터 시작) (기본: 0)
     * @param size 페이지 크기 (기본: 10)
     * @param sortBy 정렬 기준 (created_at, name, price, stock) (기본: created_at)
     * @param sortOrder 정렬 방향 (asc, desc) (기본: desc)
     * @param includeDeleted 삭제된 상품 포함 여부 (기본: false)
     * @return 페이징된 판매자 상품 목록
     */
    @Operation(summary = "판매자 상품 목록 조회", description = "판매자의 상품 목록을 조회합니다. Owner 권한이 필요합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "조회 성공"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
            ApiResponse(responseCode = "403", description = "권한 없음 (Owner 권한 필요)"),
        ],
    )
    
    @GetMapping("/owner")
    fun listOwnerProducts(
        @RequestParam storeId: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "created_at") sortBy: String,
        @RequestParam(defaultValue = "desc") sortOrder: String,
        @RequestParam(defaultValue = "false") includeDeleted: Boolean,
    ): ListOwnerProductsResponse {
        val query =
            ListOwnerProductsQuery(
                storeId = UUID.fromString(storeId),
                page = page,
                size = size,
                sortBy = sortBy,
                sortOrder = sortOrder,
                includeDeleted = includeDeleted,
            )

        val result = listOwnerProductsService.list(query)
        return ListOwnerProductsResponse.from(result)
    }

    /**
     * 상품 상세 정보를 조회합니다.
     *
     * @param id 상품 ID
     * @return 상품 상세 정보
     */
    @Operation(summary = "상품 상세 조회", description = "상품의 상세 정보를 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "조회 성공"),
            ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음"),
        ],
    )
    @GetMapping("/{id}")
    fun getProductDetail(
        @PathVariable id: UUID,
    ): GetProductDetailResponse {
        val query = GetProductDetailQuery(productId = id)
        val result = getProductDetailService.getProductDetail(query)
        return GetProductDetailResponse.from(result)
    }

    /**
     * 조건에 맞는 상품 목록을 조회합니다.
     *
     * **유사도 검색 기능:**
     * - `useSimilaritySearch=true` 파라미터로 유사도 검색 활성화
     * - PostgreSQL의 pg_trgm 확장을 사용하여 텍스트 유사도 기반 검색 수행
     * - 예: "무선 마우쓰" 검색 시 "무선 마우스" 상품도 검색 가능 (오타 허용)
     *
     * **성능 최적화:**
     * - N+1 문제 해결: 카테고리 경로를 배치로 조회
     * - 비정규화된 store_name 컬럼 활용으로 Store JOIN 불필요
     *
     * @param productName 상품명 검색 키워드 (선택)
     * @param storeName 스토어명 검색 키워드 (선택)
     * @param minPrice 최소 가격 (선택)
     * @param maxPrice 최대 가격 (선택)
     * @param sortBy 정렬 기준 (price, created_at, rating) (기본: created_at)
     * @param sortOrder 정렬 방향 (asc, desc) (기본: desc)
     * @param page 페이지 번호 (0부터 시작) (기본: 0)
     * @param size 페이지 크기 (기본: 10)
     * @param useSimilaritySearch 유사도 검색 사용 여부 (true: pg_trgm 유사도 검색, false: LIKE 부분 일치) (기본: false)
     * @return 페이징된 상품 목록
     */
    @Operation(summary = "상품 검색", description = "조건에 맞는 상품 목록을 검색합니다. 유사도 검색 기능을 지원합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "검색 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
        ],
    )
    @GetMapping
    fun searchProducts(
        @RequestParam(required = false) productName: String?,
        @RequestParam(required = false) storeName: String?,
        @RequestParam(required = false) minPrice: BigDecimal?,
        @RequestParam(required = false) maxPrice: BigDecimal?,
        @RequestParam(defaultValue = "created_at") sortBy: String,
        @RequestParam(defaultValue = "desc") sortOrder: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "false") useSimilaritySearch: Boolean,
    ): SearchProductsResponse {
        val query =
            SearchProductsQuery(
                productName = productName,
                storeName = storeName,
                minPrice = minPrice,
                maxPrice = maxPrice,
                sortBy = sortBy,
                sortOrder = sortOrder,
                page = page,
                size = size,
                useSimilaritySearch = useSimilaritySearch,
            )

        val result = searchProductsService.search(query)
        return SearchProductsResponse.from(result)
    }
}

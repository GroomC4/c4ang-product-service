package com.groom.product.presentation.web

import com.groom.product.application.service.DeleteProductService
import com.groom.product.application.service.RegisterProductService
import com.groom.product.application.service.ToggleProductHideService
import com.groom.product.application.service.UpdateProductService
import com.groom.product.common.util.IstioHeaderExtractor
import com.groom.product.presentation.web.dto.DeleteProductRequest
import com.groom.product.presentation.web.dto.DeleteProductResponse
import com.groom.product.presentation.web.dto.RegisterProductRequest
import com.groom.product.presentation.web.dto.RegisterProductResponse
import com.groom.product.presentation.web.dto.ToggleProductHideRequest
import com.groom.product.presentation.web.dto.ToggleProductHideResponse
import com.groom.product.presentation.web.dto.UpdateProductRequest
import com.groom.product.presentation.web.dto.UpdateProductResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

/**
 * 상품 명령(Command) Controller.
 *
 * 상품의 상태를 변경하는 모든 작업을 처리합니다.
 *
 * 엔드포인트:
 * - POST /api/v1/products - 상품 등록 (판매자만, AI 설명 생성 옵션 포함)
 * - PUT /api/v1/products/{id} - 상품 수정 (판매자만)
 * - PATCH /api/v1/products/{id} - 상품 삭제 (판매자만)
 * - PATCH /api/v1/products/{id}/hide - 상품 숨김/복원 (판매자만)
 *
 * 인증/인가는 Istio API Gateway에서 처리하며,
 * X-User-Id, X-User-Role 헤더를 통해 사용자 정보를 전달받습니다.
 */
@Tag(name = "Product Command", description = "상품 명령 API")
@RestController
@RequestMapping("/api/v1/products")
class ProductCommandController(
    private val registerProductService: RegisterProductService,
    private val updateProductService: UpdateProductService,
    private val deleteProductService: DeleteProductService,
    private val toggleProductHideService: ToggleProductHideService,
    private val istioHeaderExtractor: IstioHeaderExtractor,
) {
    /**
     * 새로운 상품을 등록합니다.
     *
     * 판매자(스토어 소유자)만 등록 가능합니다.
     *
     * @param request 상품 등록 정보
     * @return 등록된 상품 정보
     */
    @Operation(
        summary = "상품 등록",
        description = "새로운 상품을 등록합니다. Owner 권한이 필요합니다. AI 설명 생성 옵션을 지원합니다. " +
            "Istio API Gateway에서 X-User-Id, X-User-Role 헤더를 통해 인증/인가를 처리합니다.",
    )
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "등록 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패 (Istio Gateway)"),
            ApiResponse(responseCode = "403", description = "권한 없음 (Owner 권한 필요, Istio Gateway)"),
        ],
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun registerProduct(
        @RequestBody request: RegisterProductRequest,
        httpServletRequest: HttpServletRequest,
    ): RegisterProductResponse {
        val userId = istioHeaderExtractor.extractUserId(httpServletRequest)
        val command = request.toCommand()
        val result = registerProductService.register(userId, command)
        return RegisterProductResponse.from(result)
    }

    /**
     * 상품 정보를 수정합니다.
     *
     * 판매자(스토어 소유자)만 수정 가능합니다.
     *
     * @param id 수정할 상품 ID
     * @param request 수정할 상품 정보
     * @return 수정된 상품 정보
     */
    @Operation(summary = "상품 수정", description = "상품 정보를 수정합니다. Owner 권한이 필요합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "수정 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패 (Istio Gateway)"),
            ApiResponse(responseCode = "403", description = "권한 없음 (Owner 권한 필요, Istio Gateway)"),
            ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음"),
        ],
    )
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: UUID,
        @RequestBody request: UpdateProductRequest,
    ): UpdateProductResponse {
        val command = request.toCommand(id)
        val result = updateProductService.update(command)
        return UpdateProductResponse.from(result)
    }

    /**
     * 상품을 삭제합니다 (소프트 삭제).
     *
     * 판매자(스토어 소유자)만 삭제 가능합니다.
     *
     * @param id 삭제할 상품 ID
     * @param request 삭제 요청 정보
     * @return 삭제된 상품 정보
     */
    @Operation(summary = "상품 삭제", description = "상품을 삭제합니다 (소프트 삭제). Owner 권한이 필요합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "삭제 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패 (Istio Gateway)"),
            ApiResponse(responseCode = "403", description = "권한 없음 (Owner 권한 필요, Istio Gateway)"),
            ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음"),
        ],
    )
    @PatchMapping("/{id}")
    fun deleteProduct(
        @PathVariable id: UUID,
        @RequestBody request: DeleteProductRequest,
    ): DeleteProductResponse {
        val command = request.toCommand(id)
        val result = deleteProductService.delete(command)
        return DeleteProductResponse.from(result)
    }

    /**
     * 상품을 숨김/복원 처리합니다 (토글).
     *
     * 판매자(스토어 소유자)만 가능합니다.
     * 숨김 처리된 상품은 검색 및 목록에서 보이지 않습니다.
     *
     * @param id 상품 ID
     * @param request 숨김/복원 요청 정보
     * @return 숨김/복원 처리 결과
     */
    @Operation(summary = "상품 숨김/복원", description = "상품을 숨김/복원 처리합니다. Owner 권한이 필요합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "처리 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청"),
            ApiResponse(responseCode = "401", description = "인증 실패 (Istio Gateway)"),
            ApiResponse(responseCode = "403", description = "권한 없음 (Owner 권한 필요, Istio Gateway)"),
            ApiResponse(responseCode = "404", description = "상품을 찾을 수 없음"),
        ],
    )
    @PatchMapping("/{id}/hide")
    fun toggleProductHide(
        @PathVariable id: UUID,
        @RequestBody request: ToggleProductHideRequest,
    ): ToggleProductHideResponse {
        val command = request.toCommand(id)
        val result = toggleProductHideService.toggleHide(command)
        return ToggleProductHideResponse.from(result)
    }
}

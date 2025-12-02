package com.groom.product.adapter.inbound.web

import com.groom.product.adapter.inbound.web.dto.UploadImageResponse
import com.groom.product.application.service.UploadProductImageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

/**
 * 상품 이미지 업로드 Controller.
 *
 * 상품 이미지를 S3에 업로드하는 엔드포인트를 제공합니다.
 *
 * 엔드포인트:
 * - POST /api/v1/products/images - 이미지 업로드 (판매자만)
 */
@Tag(name = "Product Image", description = "상품 이미지 API")
@RestController
@RequestMapping("/api/v1/products/images")
class ProductImageController(
    private val uploadProductImageService: UploadProductImageService,
) {
    /**
     * 상품 이미지를 S3에 업로드합니다.
     *
     * 판매자(스토어 소유자)만 업로드 가능합니다.
     * 이미지 파일만 허용되며, 최대 10MB까지 업로드 가능합니다.
     *
     * @param file 업로드할 이미지 파일
     * @return 업로드된 이미지 정보 (URL, 파일명, 타입, 크기)
     */
    @Operation(summary = "상품 이미지 업로드", description = "상품 이미지를 S3에 업로드합니다. Owner 권한이 필요합니다. 최대 10MB까지 업로드 가능합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "업로드 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청 (파일 형식 또는 크기 오류)"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
            ApiResponse(responseCode = "403", description = "권한 없음 (Owner 권한 필요)"),
        ],
    )
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun uploadImage(
        @RequestPart("file") file: MultipartFile,
    ): UploadImageResponse {
        val result = uploadProductImageService.upload(file)
        return UploadImageResponse(
            imageUrl = result.imageUrl,
            fileName = result.fileName,
            contentType = result.contentType,
            fileSize = result.fileSize,
        )
    }
}

package com.groom.product.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 이미지 업로드 REST API Response DTO.
 */
@Schema(description = "이미지 업로드 응답")
data class UploadImageResponse(
    @Schema(description = "이미지 URL", example = "https://s3.amazonaws.com/bucket/image.jpg")
    val imageUrl: String,
    @Schema(description = "파일명", example = "product_image_12345.jpg")
    val fileName: String,
    @Schema(description = "컨텐츠 타입", example = "image/jpeg")
    val contentType: String?,
    @Schema(description = "파일 크기 (바이트)", example = "1048576")
    val fileSize: Long,
)

package com.groom.product.application.service

import com.groom.product.adapter.outbound.storage.S3ImageUploader
import com.groom.product.domain.service.ImageUploadPolicy
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

private val logger = KotlinLogging.logger {}

/**
 * 상품 이미지 업로드 애플리케이션 서비스
 *
 * 이미지 업로드 유스케이스를 조율합니다.
 * - 도메인 정책 검증 (ImageUploadPolicy)
 * - 인프라 업로드 실행 (S3ImageUploader)
 */
@Service
class UploadProductImageService(
    private val s3ImageUploader: S3ImageUploader,
) {
    /**
     * 상품 이미지를 업로드합니다.
     *
     * @param file 업로드할 이미지 파일
     * @return 업로드된 이미지 정보
     */
    fun upload(file: MultipartFile): UploadImageResult {
        logger.info { "상품 이미지 업로드 시작: originalFilename=${file.originalFilename}, size=${file.size}" }

        // 1. 도메인 정책 검증
        ImageUploadPolicy.validate(file)

        // 2. 인프라 업로드 실행
        val uploadResult = s3ImageUploader.upload(file)

        logger.info { "상품 이미지 업로드 완료: imageUrl=${uploadResult.imageUrl}" }

        return UploadImageResult(
            imageUrl = uploadResult.imageUrl,
            fileName = uploadResult.fileName,
            contentType = uploadResult.contentType,
            fileSize = uploadResult.fileSize,
        )
    }

    /**
     * 이미지 업로드 결과
     */
    data class UploadImageResult(
        val imageUrl: String,
        val fileName: String,
        val contentType: String,
        val fileSize: Long,
    )
}

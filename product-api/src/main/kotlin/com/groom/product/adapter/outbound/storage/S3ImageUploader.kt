package com.groom.product.adapter.out.storage

import com.groom.product.common.configuration.S3Properties
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.time.Instant
import kotlin.random.Random

private val logger = KotlinLogging.logger {}

/**
 * S3 이미지 업로더 (Infrastructure 어댑터)
 *
 * AWS S3에 이미지를 업로드하는 구현체입니다.
 */
@Component
open class S3ImageUploader(
    private val s3Client: S3Client,
    private val s3Properties: S3Properties,
) {
    /**
     * 이미지를 S3에 업로드
     *
     * @param file 업로드할 파일
     * @return 업로드 결과 (URL, 파일명, 메타데이터)
     */
    fun upload(file: MultipartFile): UploadResult {
        val fileName = generateUniqueFileName(file.originalFilename ?: "image")
        val contentType = file.contentType ?: "application/octet-stream"

        logger.info { "S3 이미지 업로드 시작: fileName=$fileName, contentType=$contentType, size=${file.size}" }

        try {
            val putObjectRequest =
                PutObjectRequest
                    .builder()
                    .bucket(s3Properties.bucketName)
                    .key(fileName)
                    .contentType(contentType)
                    .build()

            s3Client.putObject(
                putObjectRequest,
                RequestBody.fromInputStream(file.inputStream, file.size),
            )

            val imageUrl = buildImageUrl(fileName)
            logger.info { "S3 이미지 업로드 완료: imageUrl=$imageUrl" }

            return UploadResult(
                imageUrl = imageUrl,
                fileName = fileName,
                contentType = contentType,
                fileSize = file.size,
            )
        } catch (e: Exception) {
            logger.error(e) { "S3 이미지 업로드 실패: fileName=$fileName" }
            throw IllegalStateException("이미지 업로드 중 오류가 발생했습니다.", e)
        }
    }

    /**
     * 고유한 파일 이름 생성
     *
     * 형식: PRODUCT_{timestamp}_{randomString}.{extension}
     */
    private fun generateUniqueFileName(originalFileName: String): String {
        val extension = originalFileName.substringAfterLast(".", "")
        val timestamp = Instant.now().toEpochMilli()
        val randomString = generateRandomString(16)
        val fileName = "PRODUCT_${timestamp}_$randomString"

        return if (extension.isNotEmpty()) {
            "products/$fileName.$extension"
        } else {
            "products/$fileName"
        }
    }

    /**
     * 랜덤 문자열 생성 (알파벳 소문자 + 숫자)
     */
    private fun generateRandomString(length: Int): String {
        val chars = "abcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { chars[Random.nextInt(chars.length)] }
            .joinToString("")
    }

    /**
     * S3 이미지 URL 생성
     */
    private fun buildImageUrl(fileName: String): String =
        "https://${s3Properties.bucketName}.s3.${s3Properties.region}.amazonaws.com/$fileName"

    /**
     * 업로드 결과
     */
    data class UploadResult(
        val imageUrl: String,
        val fileName: String,
        val contentType: String,
        val fileSize: Long,
    )
}

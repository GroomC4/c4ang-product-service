package com.groom.product.domain.service

import org.springframework.web.multipart.MultipartFile

/**
 * 이미지 업로드 정책 (도메인 서비스)
 *
 * 이미지 업로드 시 준수해야 하는 비즈니스 규칙을 정의합니다.
 */
class ImageUploadPolicy {
    companion object {
        private val ALLOWED_EXTENSIONS = setOf("jpg", "jpeg", "png", "gif", "webp", "bmp", "svg")
        private const val MAX_FILE_SIZE = 10 * 1024 * 1024 // 10MB

        /**
         * 이미지 파일 유효성 검증
         *
         * @param file 검증할 파일
         * @throws IllegalArgumentException 검증 실패 시
         */
        fun validate(file: MultipartFile) {
            validateNotEmpty(file)
            validateContentType(file)
            validateExtension(file)
            validateFileSize(file)
        }

        /**
         * 파일이 비어있지 않은지 검증
         */
        private fun validateNotEmpty(file: MultipartFile) {
            if (file.isEmpty) {
                throw IllegalArgumentException("업로드할 파일이 비어있습니다.")
            }
        }

        /**
         * Content-Type 검증
         */
        private fun validateContentType(file: MultipartFile) {
            val contentType = file.contentType ?: ""
            if (!contentType.startsWith("image/")) {
                throw IllegalArgumentException("이미지 파일만 업로드 가능합니다. (현재 타입: $contentType)")
            }
        }

        /**
         * 파일 확장자 검증
         */
        private fun validateExtension(file: MultipartFile) {
            val fileName = file.originalFilename ?: ""
            val extension = fileName.substringAfterLast(".", "").lowercase()

            if (extension.isEmpty() || extension !in ALLOWED_EXTENSIONS) {
                throw IllegalArgumentException(
                    "허용되지 않는 파일 확장자입니다. (현재: $extension, 허용: ${ALLOWED_EXTENSIONS.joinToString(", ")})",
                )
            }
        }

        /**
         * 파일 크기 검증
         */
        private fun validateFileSize(file: MultipartFile) {
            if (file.size > MAX_FILE_SIZE) {
                throw IllegalArgumentException(
                    "파일 크기는 10MB를 초과할 수 없습니다. (현재 크기: ${file.size / 1024 / 1024}MB)",
                )
            }
        }
    }
}

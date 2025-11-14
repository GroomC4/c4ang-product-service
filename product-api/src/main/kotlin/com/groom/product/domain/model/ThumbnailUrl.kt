package com.groom.product.domain.model

import java.net.URL

/**
 * 썸네일 URL 값 객체.
 * 유효한 URL 형식이어야 하며, HTTP 또는 HTTPS 프로토콜만 허용합니다.
 */
data class ThumbnailUrl(
    val value: String,
) {
    init {
        require(value.isNotBlank()) { "썸네일 URL은 비어있을 수 없습니다." }

        try {
            val url = URL(value)
            require(url.protocol in listOf("http", "https")) {
                "썸네일 URL은 HTTP 또는 HTTPS 프로토콜이어야 합니다. (입력값: ${url.protocol})"
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("유효하지 않은 URL 형식입니다. (입력값: $value)", e)
        }

        require(value.length <= MAX_LENGTH) {
            "썸네일 URL은 ${MAX_LENGTH}자를 초과할 수 없습니다. (입력값: ${value.length}자)"
        }
    }

    companion object {
        private const val MAX_LENGTH = 500

        fun from(value: String): ThumbnailUrl {
            val trimmed = value.trim()
            return ThumbnailUrl(trimmed)
        }
    }
}
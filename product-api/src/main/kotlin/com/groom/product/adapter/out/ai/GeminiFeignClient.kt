package com.groom.product.adapter.out.ai

import com.groom.product.adapter.out.ai.GeminiProductDescriptionGenerator.Candidate
import com.groom.product.adapter.out.ai.GeminiProductDescriptionGenerator.Content
import com.groom.product.adapter.out.ai.GeminiProductDescriptionGenerator.GeminiRequest
import com.groom.product.adapter.out.ai.GeminiProductDescriptionGenerator.GeminiResponse
import com.groom.product.adapter.out.ai.GeminiProductDescriptionGenerator.GenerationConfig
import com.groom.product.adapter.out.ai.GeminiProductDescriptionGenerator.Part
import com.groom.product.adapter.out.ai.GeminiProductDescriptionGenerator.SystemInstruction
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * Google Gemini API를 위한 Feign Client.
 *
 * Gemini REST API와 통신하기 위한 선언적 HTTP 클라이언트입니다.
 * URL은 GeminiFeignConfig에서 동적으로 설정됩니다.
 */
@FeignClient(
    name = "gemini-api",
    url = "\${google.gemini.base-url:https://generativelanguage.googleapis.com/v1beta/models}",
    configuration = [GeminiFeignConfig::class],
)
interface GeminiFeignClient {
    /**
     * Gemini API의 generateContent 엔드포인트를 호출합니다.
     *
     * @param model 사용할 Gemini 모델 이름
     * @param request Gemini API 요청 본문
     * @return Gemini API 응답
     */
    @PostMapping("/{model}:generateContent")
    fun generateContent(
        @PathVariable("model") model: String,
        @RequestBody request: GeminiRequest,
    ): GeminiResponse
}

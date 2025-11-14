package com.groom.product.infrastructure.adapter

import com.groom.product.common.configuration.GeminiProperties
import com.groom.product.domain.port.ProductDescriptionGenerationException
import com.groom.product.domain.port.ProductDescriptionGenerator
import com.groom.product.infrastructure.client.GeminiFeignClient
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

/**
 * Google Gemini AI를 사용한 상품 설명 생성 어댑터.
 *
 * Feign Client를 사용하여 Gemini REST API를 호출하고,
 * 사용자의 프롬프트를 기반으로 상품 설명을 자동 생성합니다.
 */
@Component
class GeminiProductDescriptionGenerator(
    private val geminiProperties: GeminiProperties,
    private val geminiFeignClient: GeminiFeignClient,
) : ProductDescriptionGenerator {
    private val logger = KotlinLogging.logger {}

    companion object {
        private const val SYSTEM_INSTRUCTION =
            """당신은 전문적인 상품 설명 작성자입니다.
사용자가 요청한 상품에 대해 구매를 유도할 수 있는 매력적이고 간결한 설명을 작성해주세요.

규칙:
1. 상품 설명만 작성하고, 다른 내용은 포함하지 마세요.
2. 최대 500자 이내로 작성하세요.
3. 만약 사용자의 요청이 상품 설명 작성과 관련이 없다면, 정확히 "NOT_PRODUCT_DESCRIPTION"이라고만 응답하세요.
4. 상품의 특징, 장점, 사용 용도를 명확하게 설명하세요.
5. 고객의 관점에서 작성하세요.
"""

        private const val NOT_PRODUCT_DESCRIPTION_MARKER = "NOT_PRODUCT_DESCRIPTION"
    }

    override fun generate(prompt: String): String =
        try {
            logger.info { "Gemini API 호출 시작 - prompt: $prompt" }

            val response = callGeminiApi(prompt)
            val generatedText = response.extractText()

            logger.info { "Gemini API 응답 수신 - length: ${generatedText.length}" }

            validateResponse(generatedText, prompt)

            generatedText
        } catch (e: ProductDescriptionGenerationException) {
            logger.warn(e) { "상품 설명 생성 실패 - prompt: $prompt" }
            throw e
        } catch (e: Exception) {
            logger.error(e) { "Gemini API 호출 실패 - prompt: $prompt, error: ${e.message}" }
            throw ProductDescriptionGenerationException.ServiceUnavailable(e)
        }

    private fun callGeminiApi(prompt: String): GeminiResponse {
        logger.info { "Gemini API 호출 - model: ${geminiProperties.model}" }

        val request =
            GeminiRequest(
                systemInstruction =
                    SystemInstruction(
                        parts = listOf(Part(text = SYSTEM_INSTRUCTION)),
                    ),
                contents =
                    listOf(
                        Content(
                            parts =
                                listOf(
                                    Part(text = prompt),
                                ),
                        ),
                    ),
                generationConfig =
                    GenerationConfig(
                        temperature = 0.7,
                        topK = 40,
                        topP = 0.95,
                        maxOutputTokens = geminiProperties.maxTokens,
                    ),
            )

        return geminiFeignClient.generateContent(geminiProperties.model, request)
    }

    private fun validateResponse(
        generatedText: String,
        prompt: String,
    ) {
        if (generatedText.contains(NOT_PRODUCT_DESCRIPTION_MARKER, ignoreCase = true)) {
            throw ProductDescriptionGenerationException.InvalidPrompt(prompt)
        }

        if (generatedText.isBlank()) {
            throw ProductDescriptionGenerationException.ServiceUnavailable(
                IllegalStateException("🤖안타깝게도 드릴 말씀이 없습니다🤖"),
            )
        }
    }

    // Gemini API Request/Response DTOs

    /**
     * Gemini API 요청 본문.
     *
     * @property systemInstruction AI 모델의 동작 방식을 정의하는 시스템 지침 (옵션)
     * @property contents 사용자가 전달하는 실제 입력 내용 (프롬프트)
     * @property generationConfig 텍스트 생성 시 사용할 파라미터 설정
     */
    data class GeminiRequest(
        val systemInstruction: SystemInstruction? = null,
        val contents: List<Content>,
        val generationConfig: GenerationConfig,
    )

    /**
     * AI 모델의 역할과 동작 방식을 정의하는 시스템 지침.
     * 예: "당신은 전문적인 상품 설명 작성자입니다."
     *
     * @property parts 시스템 지침 텍스트를 담은 파트 리스트
     */
    data class SystemInstruction(
        val parts: List<Part>,
    )

    /**
     * 사용자 입력 콘텐츠 (프롬프트).
     * 여러 파트로 구성될 수 있으며, 텍스트, 이미지 등을 포함할 수 있음.
     *
     * @property parts 실제 입력 데이터를 담은 파트 리스트
     */
    data class Content(
        val parts: List<Part>,
    )

    /**
     * 콘텐츠의 개별 파트 (텍스트 조각).
     * 현재는 텍스트만 지원하지만, Gemini API는 이미지 등도 지원 가능.
     *
     * @property text 입력 텍스트 내용
     */
    data class Part(
        val text: String,
    )

    /**
     * AI 텍스트 생성 시 사용할 파라미터 설정.
     *
     * @property temperature 생성 텍스트의 창의성 조절 (0.0~1.0)
     *                      - 낮을수록 일관되고 예측 가능한 결과
     *                      - 높을수록 다양하고 창의적인 결과
     * @property topK 샘플링 시 고려할 상위 K개 토큰 수
     *                - 작을수록 더 결정적(deterministic)
     * @property topP 누적 확률 기반 샘플링 (0.0~1.0)
     *                - 확률의 합이 topP에 도달할 때까지의 토큰만 고려
     * @property maxOutputTokens 생성할 최대 토큰 수 (응답 길이 제한)
     */
    data class GenerationConfig(
        val temperature: Double,
        val topK: Int,
        val topP: Double,
        val maxOutputTokens: Int,
    )

    /**
     * Gemini API 응답 본문.
     * 여러 후보 응답이 반환될 수 있으며, 일반적으로 첫 번째 후보를 사용.
     *
     * @property candidates AI가 생성한 후보 응답 리스트 (nullable)
     */
    data class GeminiResponse(
        val candidates: List<Candidate>?,
    ) {
        /**
         * 응답에서 생성된 텍스트를 추출합니다.
         *
         * @return 생성된 텍스트, 또는 빈 문자열
         */
        fun extractText(): String =
            candidates
                ?.firstOrNull()
                ?.content
                ?.parts
                ?.firstOrNull()
                ?.text
                ?.trim()
                ?: ""
    }

    /**
     * AI가 생성한 개별 후보 응답.
     * 여러 후보가 생성될 수 있으며, 각 후보는 콘텐츠와 안전성 등급 등을 포함.
     *
     * @property content 생성된 응답 콘텐츠 (nullable)
     */
    data class Candidate(
        val content: ContentResponse?,
    )

    /**
     * 생성된 응답의 콘텐츠.
     * 요청의 Content와 유사한 구조이지만, AI가 생성한 출력을 담음.
     *
     * @property parts 생성된 텍스트를 담은 파트 리스트 (nullable)
     */
    data class ContentResponse(
        val parts: List<PartResponse>?,
    )

    /**
     * 응답 콘텐츠의 개별 파트 (생성된 텍스트 조각).
     *
     * @property text 생성된 텍스트 내용 (nullable)
     */
    data class PartResponse(
        val text: String?,
    )
}

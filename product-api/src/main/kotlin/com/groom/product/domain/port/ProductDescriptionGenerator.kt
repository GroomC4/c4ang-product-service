package com.groom.product.domain.port

/**
 * 상품 설명 생성 포트 인터페이스.
 *
 * AI를 활용하여 상품 설명을 자동으로 생성합니다.
 * Infrastructure 계층에서 실제 AI 서비스(예: Google Gemini)와 연동하여 구현됩니다.
 */
interface ProductDescriptionGenerator {
    /**
     * 프롬프트를 기반으로 상품 설명을 생성합니다.
     *
     * @param prompt 상품 설명 생성을 위한 프롬프트 (예: "스마트폰에 대한 설명")
     * @return 생성된 상품 설명
     * @throws ProductDescriptionGenerationException 설명 생성에 실패한 경우
     */
    fun generate(prompt: String): String
}

/**
 * 상품 설명 생성 중 발생하는 예외
 */
sealed class ProductDescriptionGenerationException(
    message: String,
    cause: Throwable? = null,
) : RuntimeException(message, cause) {
    /**
     * AI 서비스 호출 실패
     */
    data class ServiceUnavailable(
        override val cause: Throwable?,
    ) : ProductDescriptionGenerationException("🤖AI 서비스에 연결할 수 없습니다🤖", cause)

    /**
     * 부적절한 프롬프트 (상품 설명 요청이 아닌 경우)
     */
    data class InvalidPrompt(
        val prompt: String,
    ) : ProductDescriptionGenerationException("🤖상품 설명 생성 요청이 아닙니다🤖")

    /**
     * 프롬프트 길이 초과
     */
    data class PromptTooLong(
        val actualLength: Int,
        val maxLength: Int,
    ) : ProductDescriptionGenerationException("🤖요약해서 말해주시겠습니까? 저는 ${maxLength}자까지 이해할수있습니다(${actualLength}자를 받았습니다)🤖")
}

package com.groom.product.adapter.inbound.internal

import com.groom.product.adapter.inbound.internal.dto.ProductNotFoundErrorResponse
import com.groom.product.common.exception.ProductException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Internal API 전용 Exception Handler.
 *
 * Internal API는 Consumer와의 Contract에 정의된 에러 응답 형식을 따라야 합니다.
 * - error: 에러 코드 (예: "PRODUCT_NOT_FOUND")
 * - message: 에러 메시지
 *
 * 이 핸들러는 GlobalExceptionHandler보다 높은 우선순위를 가지며,
 * Internal API Controller에서 발생한 예외만 처리합니다.
 */
@RestControllerAdvice(basePackageClasses = [ProductInternalController::class])
@Order(Ordered.HIGHEST_PRECEDENCE)
class InternalApiExceptionHandler {
    private val logger = KotlinLogging.logger {}

    @ExceptionHandler(ProductException.ProductNotFound::class)
    fun handleProductNotFound(e: ProductException.ProductNotFound): ResponseEntity<ProductNotFoundErrorResponse> {
        logger.warn(e) { "Internal API - Product not found: ${e.productId}" }

        return ResponseEntity(
            ProductNotFoundErrorResponse(
                error = "PRODUCT_NOT_FOUND",
                message = "Product not found with id: ${e.productId}",
            ),
            HttpStatus.NOT_FOUND,
        )
    }
}

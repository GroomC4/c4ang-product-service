package com.groom.product.configuration.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * 로컬 개발 환경용 Mock 인증 필터.
 *
 * Istio 없이 로컬 개발 시 X-User-Id, X-User-Role 헤더를 자동으로 주입합니다.
 * @Profile("local")로 설정되어 로컬 환경에서만 활성화됩니다.
 */
@Profile("local")
@Component
class LocalDevAuthFilter : OncePerRequestFilter() {
    companion object {
        private const val DEFAULT_USER_ID = "00000000-0000-0000-0000-000000000001"
        private const val DEFAULT_USER_ROLE = "OWNER"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if (request.getHeader("X-User-Id") == null) {
            val wrapper =
                object : HttpServletRequestWrapper(request) {
                    override fun getHeader(name: String): String? =
                        when (name) {
                            "X-User-Id" -> DEFAULT_USER_ID
                            "X-User-Role" -> DEFAULT_USER_ROLE
                            else -> super.getHeader(name)
                        }

                    override fun getHeaders(name: String): java.util.Enumeration<String> {
                        val header = getHeader(name)
                        return if (header != null) {
                            java.util.Collections.enumeration(listOf(header))
                        } else {
                            super.getHeaders(name)
                        }
                    }
                }
            filterChain.doFilter(wrapper, response)
            return
        }
        filterChain.doFilter(request, response)
    }
}

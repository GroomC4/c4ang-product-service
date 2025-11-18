package com.groom.product.common.extension

import com.groom.platform.testSupport.BaseContainerExtension
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.io.File

/**
 * Product Service용 통합 테스트 컨테이너 Extension
 *
 * c4ang-platform-core의 BaseContainerExtension을 상속받아 Product Service에 필요한
 * Docker Compose 파일과 스키마 파일 경로를 제공합니다.
 */
class SharedContainerExtension : BaseContainerExtension() {
    companion object {
        init {
            // 클래스 로딩 시점에 컨테이너를 시작하도록 강제
            println("📦 Initializing SharedContainerExtension...")
        }
    }

    override fun getComposeFile(): File = resolveComposeFile("c4ang-platform-core/docker-compose/test/docker-compose-integration-test.yml")

    override fun getSchemaFile(): File {
        // Product Service의 PostgreSQL 스키마 파일
        return resolveComposeFile("product-api/sql/schema.sql")
    }

    override fun beforeAll(context: ExtensionContext) {
        println("🔧 SharedContainerExtension.beforeAll() called")
        super.beforeAll(context)
    }
}

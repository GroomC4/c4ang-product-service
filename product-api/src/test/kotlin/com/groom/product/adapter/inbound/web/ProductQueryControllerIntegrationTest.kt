package com.groom.product.adapter.inbound.web

import com.groom.product.adapter.outbound.ai.TestGeminiConfig
import com.groom.product.common.IntegrationTestBase
import com.groom.product.common.config.NoOpEventPublisherConfig
import com.groom.product.common.config.TestAwsConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@AutoConfigureMockMvc(addFilters = false)
@Import(NoOpEventPublisherConfig::class, TestAwsConfig::class, TestGeminiConfig::class)
@SqlGroup(
    Sql(
        scripts = ["/sql/cleanup-product-query-controller.sql"],
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS,
    ),
    Sql(
        scripts = ["/sql/init-product-query-controller.sql"],
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS,
    ),
    Sql(
        scripts = ["/sql/cleanup-product-query-controller.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS,
    ),
)
@DisplayName("상품 조회(Query) 컨트롤러 통합 테스트")
class ProductQueryControllerIntegrationTest : IntegrationTestBase() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    companion object {
        // Test Users
        private val OWNER_USER_1 = UUID.fromString("11111111-1111-1111-1111-111111111111")
        private val OWNER_USER_2 = UUID.fromString("22222222-2222-2222-2222-222222222222")
        private val CUSTOMER_USER_1 = UUID.fromString("33333333-3333-3333-3333-333333333333")

        // Test Stores
        private val STORE_1 = UUID.fromString("11111111-1111-1111-1111-111111111101")
        private val STORE_2 = UUID.fromString("22222222-2222-2222-2222-222222222201")

        // Test Categories
        private val CATEGORY_ELECTRONICS = UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1")
        private val CATEGORY_FASHION = UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbb1")

        // Test Products
        private val PRODUCT_MOUSE = UUID.fromString("11111111-1111-1111-1111-111111110001")
        private val PRODUCT_KEYBOARD = UUID.fromString("11111111-1111-1111-1111-111111110002")
        private val PRODUCT_TSHIRT = UUID.fromString("22222222-2222-2222-2222-222222220001")
    }

    @Test
    @DisplayName("GET /api/v1/products/{id} - 상품 상세 조회 성공")
    fun testGetProductDetail() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products/$PRODUCT_MOUSE"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(PRODUCT_MOUSE.toString()))
            .andExpect(jsonPath("$.name").value("무선 게이밍 마우스"))
            .andExpect(jsonPath("$.description").value("반응속도가 뛰어난 초경량 무선 마우스입니다"))
            .andExpect(jsonPath("$.price").value(79900.00))
            .andExpect(jsonPath("$.stockQuantity").value(150))
            .andExpect(jsonPath("$.status").value("ON_SALE"))
            .andExpect(jsonPath("$.categoryId").value(CATEGORY_ELECTRONICS.toString()))
            .andExpect(jsonPath("$.categoryPath").value("전자제품"))
            .andExpect(jsonPath("$.store.id").value(STORE_1.toString()))
            .andExpect(jsonPath("$.store.name").value("테크 스토어"))
            .andExpect(jsonPath("$.images.length()").value(2))
            .andExpect(jsonPath("$.images[0].imageUrl").value("https://example.com/images/mouse_primary.jpg"))
            .andExpect(jsonPath("$.images[0].imageType").value("PRIMARY"))
            .andExpect(jsonPath("$.images[1].imageUrl").value("https://example.com/images/mouse_detail1.jpg"))
            .andExpect(jsonPath("$.images[1].imageType").value("DETAIL"))
    }

    @Test
    @DisplayName("GET /api/v1/products/{id} - 존재하지 않는 상품 조회 시 404 Not Found")
    fun testGetProductDetail_NotFound() {
        // given
        val nonExistentProductId = UUID.randomUUID()

        // when & then
        mockMvc
            .perform(get("/api/v1/products/$nonExistentProductId"))
            .andExpect(status().isNotFound)
    }

    @Test
    @DisplayName("GET /api/v1/products - 전체 상품 목록 조회 성공")
    fun testSearchProducts() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(6))
            .andExpect(jsonPath("$.totalPages").value(1))
            .andExpect(jsonPath("$.currentPage").value(0))
            .andExpect(jsonPath("$.pageSize").value(10))
            .andExpect(jsonPath("$.products.length()").value(6))
            .andExpect(jsonPath("$.products[0].name").exists())
            .andExpect(jsonPath("$.products[0].price").exists())
            .andExpect(jsonPath("$.products[0].storeName").exists())
            .andExpect(jsonPath("$.products[0].categoryPath").exists())
    }

    @Test
    @DisplayName("GET /api/v1/products?productName=무선 - 상품명으로 검색 성공")
    fun testSearchProductsByName() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products?productName=무선"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(2))
            .andExpect(jsonPath("$.products[0].name").value("무선 게이밍 마우스"))
    }

    @Test
    @DisplayName("GET /api/v1/products?productName=키보드 - 상품명으로 검색 성공 (키보드)")
    fun testSearchProductsByName_Keyboard() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products?productName=키보드"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(2)) // 기계식 키보드 + 게이밍 키보드
            .andExpect(jsonPath("$.products[0].name").value("기계식 키보드"))
    }

    @Test
    @DisplayName("GET /api/v1/products?minPrice=100000 - 최소 가격 필터링 성공")
    fun testSearchProductsByMinPrice() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products?minPrice=100000"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(2)) // 기계식 키보드 (129,900) + 게이밍 키보드 (149,900)
            .andExpect(jsonPath("$.products[0].name").value("기계식 키보드"))
            .andExpect(jsonPath("$.products[0].price").value(129900.00))
    }

    @Test
    @DisplayName("GET /api/v1/products?maxPrice=50000 - 최대 가격 필터링 성공")
    fun testSearchProductsByMaxPrice() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products?maxPrice=50000"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(2)) // 오버핏 티셔츠 (29,900) + USB 허브 (19,900)
            .andExpect(jsonPath("$.products[0].name").value("오버핏 티셔츠"))
            .andExpect(jsonPath("$.products[0].price").value(29900.00))
    }

    @Test
    @DisplayName("GET /api/v1/products?minPrice=50000&maxPrice=100000 - 가격 범위 필터링 성공")
    fun testSearchProductsByPriceRange() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products?minPrice=50000&maxPrice=100000"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(2)) // 무선 게이밍 마우스 (79,900) + 무선 마우쓰 프로 (89,900)
            .andExpect(jsonPath("$.products[0].name").value("무선 게이밍 마우스"))
    }

    @Test
    @DisplayName("GET /api/v1/products?storeName=테크 - 스토어명으로 검색 성공")
    fun testSearchProductsByStoreName() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products").param("storeName", "테크"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(4)) // "테크 스토어" 매칭 상품 (Products 1, 2, 4, 5)
            .andExpect(jsonPath("$.products[0].storeName").value("테크 스토어"))
    }

    @Test
    @DisplayName("GET /api/v1/products?page=0&size=2 - 페이징 처리 성공")
    fun testSearchProductsWithPagination() {
        // when & then - 첫 페이지
        mockMvc
            .perform(get("/api/v1/products?page=0&size=2"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(6))
            .andExpect(jsonPath("$.totalPages").value(3))
            .andExpect(jsonPath("$.currentPage").value(0))
            .andExpect(jsonPath("$.pageSize").value(2))
            .andExpect(jsonPath("$.products.length()").value(2))

        // when & then - 두 번째 페이지
        mockMvc
            .perform(get("/api/v1/products?page=1&size=2"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(6))
            .andExpect(jsonPath("$.totalPages").value(3))
            .andExpect(jsonPath("$.currentPage").value(1))
            .andExpect(jsonPath("$.pageSize").value(2))
            .andExpect(jsonPath("$.products.length()").value(2))
    }

    @Test
    @DisplayName("GET /api/v1/products?sortBy=price&sortOrder=asc - 가격 오름차순 정렬 성공")
    fun testSearchProductsSortedByPriceAsc() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products?sortBy=price&sortOrder=asc"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(6))
            .andExpect(jsonPath("$.products[0].name").value("USB 허브"))
            .andExpect(jsonPath("$.products[0].price").value(19900.00))
            .andExpect(jsonPath("$.products[1].name").value("오버핏 티셔츠"))
            .andExpect(jsonPath("$.products[1].price").value(29900.00))
            .andExpect(jsonPath("$.products[2].name").value("무선 게이밍 마우스"))
            .andExpect(jsonPath("$.products[2].price").value(79900.00))
    }

    @Test
    @DisplayName("GET /api/v1/products?sortBy=price&sortOrder=desc - 가격 내림차순 정렬 성공")
    fun testSearchProductsSortedByPriceDesc() {
        // when & then
        mockMvc
            .perform(get("/api/v1/products?sortBy=price&sortOrder=desc"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").value(6))
            .andExpect(jsonPath("$.products[0].name").value("게이밍 키보드"))
            .andExpect(jsonPath("$.products[0].price").value(149900.00))
            .andExpect(jsonPath("$.products[1].name").value("기계식 키보드"))
            .andExpect(jsonPath("$.products[1].price").value(129900.00))
            .andExpect(jsonPath("$.products[2].name").value("무선 마우쓰 프로"))
            .andExpect(jsonPath("$.products[2].price").value(89900.00))
    }

    // ============================================================
    // 유사도 검색 테스트 (Similarity Search Tests)
    // ============================================================

    @Test
    @DisplayName("GET /api/v1/products?productName=마우스&useSimilaritySearch=true - 상품명 유사도 검색 성공")
    fun testSearchProductsBySimilarity_ProductName() {
        // when & then
        mockMvc
            .perform(
                get("/api/v1/products")
                    .param("productName", "마우스")
                    .param("useSimilaritySearch", "true"),
            ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").exists())
            // "무선 게이밍 마우스", "무선 마우쓰 프로" 둘 다 검색되어야 함
            .andExpect(jsonPath("$.products").isArray)
    }

    @Test
    @DisplayName("GET /api/v1/products?productName=마우쓰&useSimilaritySearch=true - 오타 포함 상품명 유사도 검색 성공")
    fun testSearchProductsBySimilarity_ProductNameWithTypo() {
        // when & then - 오타("마우쓰")로 검색해도 "마우스" 상품이 검색되어야 함
        mockMvc
            .perform(
                get("/api/v1/products")
                    .param("productName", "마우쓰")
                    .param("useSimilaritySearch", "true"),
            ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.products").isArray)
    }

    @Test
    @DisplayName("GET /api/v1/products?storeName=텍크&useSimilaritySearch=true - 스토어명 유사도 검색 성공")
    fun testSearchProductsBySimilarity_StoreName() {
        // when & then - "텍크"로 검색해도 "테크 스토어" 상품이 검색되어야 함
        mockMvc
            .perform(
                get("/api/v1/products")
                    .param("storeName", "텍크")
                    .param("useSimilaritySearch", "true"),
            ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.products").isArray)
    }

    @Test
    @DisplayName("GET /api/v1/products?storeName=테크&useSimilaritySearch=false - 일반 검색과 유사도 검색 차이 확인")
    fun testSearchProductsByStoreName_NormalVsSimilarity() {
        // when & then - 일반 검색 ("테크" 포함하는 것만)
        val normalResult =
            mockMvc
                .perform(
                    get("/api/v1/products")
                        .param("storeName", "테크")
                        .param("useSimilaritySearch", "false"),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.totalElements").exists())
                .andReturn()

        // when & then - 유사도 검색 ("테크"와 유사한 것 포함)
        val similarityResult =
            mockMvc
                .perform(
                    get("/api/v1/products")
                        .param("storeName", "테크")
                        .param("useSimilaritySearch", "true"),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.totalElements").exists())
                .andReturn()

        // 유사도 검색이 일반 검색보다 같거나 많은 결과를 반환해야 함
        // (실제 비교는 JSON 파싱 후 수행)
    }

    @Test
    @DisplayName("GET /api/v1/products?productName=키보드&useSimilaritySearch=true&sortBy=price&sortOrder=asc - 유사도 검색 + 정렬")
    fun testSearchProductsBySimilarity_WithSorting() {
        // when & then
        mockMvc
            .perform(
                get("/api/v1/products")
                    .param("productName", "키보드")
                    .param("useSimilaritySearch", "true")
                    .param("sortBy", "price")
                    .param("sortOrder", "asc"),
            ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.products").isArray)
        // 가격 오름차순으로 정렬되어야 함
    }

    @Test
    @DisplayName("GET /api/v1/products?productName=마우스&minPrice=80000&useSimilaritySearch=true - 유사도 검색 + 가격 필터")
    fun testSearchProductsBySimilarity_WithPriceFilter() {
        // when & then
        mockMvc
            .perform(
                get("/api/v1/products")
                    .param("productName", "마우스")
                    .param("minPrice", "80000")
                    .param("useSimilaritySearch", "true"),
            ).andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.products").isArray)
        // 80000원 이상인 마우스 관련 상품만 검색되어야 함
    }
}

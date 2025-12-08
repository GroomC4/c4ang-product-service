package com.groom.product.adapter.inbound.internal

import com.fasterxml.jackson.databind.ObjectMapper
import com.groom.product.adapter.inbound.internal.dto.rag.RagCompareProductsRequest
import com.groom.product.adapter.inbound.internal.dto.rag.RagProductSearchRequest
import com.groom.product.adapter.outbound.ai.TestGeminiConfig
import com.groom.product.common.IntegrationTestBase
import com.groom.product.common.config.NoOpEventPublisherConfig
import com.groom.product.common.config.TestAwsConfig
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.UUID

@AutoConfigureMockMvc(addFilters = false)
@Import(NoOpEventPublisherConfig::class, TestAwsConfig::class, TestGeminiConfig::class)
@SqlGroup(
    Sql(
        scripts = ["/sql/cleanup-rag-internal-controller.sql"],
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS,
    ),
    Sql(
        scripts = ["/sql/init-rag-internal-controller.sql"],
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS,
    ),
    Sql(
        scripts = ["/sql/cleanup-rag-internal-controller.sql"],
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS,
    ),
)
@DisplayName("RAG Internal API 통합 테스트")
class RagInternalControllerIntegrationTest : IntegrationTestBase() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        // Test Perfume IDs
        private val PERFUME_CHANEL_COCO = UUID.fromString("aa000000-0000-0000-0000-000000000201")
        private val PERFUME_DIOR_MISS = UUID.fromString("aa000000-0000-0000-0000-000000000202")
        private val PERFUME_DIOR_SAUVAGE = UUID.fromString("aa000000-0000-0000-0000-000000000203")
        private val PERFUME_JOMALONE = UUID.fromString("aa000000-0000-0000-0000-000000000204")
        private val PERFUME_CHANEL_NO5 = UUID.fromString("aa000000-0000-0000-0000-000000000205")
    }

    @Nested
    @DisplayName("POST /internal/v1/rag/perfumes/search - 향수 검색")
    inner class SearchPerfumes {
        @Test
        @DisplayName("productIds로 검색 시 해당 향수만 반환")
        fun searchByProductIds() {
            // given
            val request =
                RagProductSearchRequest(
                    productIds = listOf(PERFUME_CHANEL_COCO, PERFUME_DIOR_MISS),
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.totalCount").value(2))
                .andExpect(jsonPath("$.results").isArray)
                .andExpect(jsonPath("$.results.length()").value(2))
        }

        @Test
        @DisplayName("brand로 검색 시 해당 브랜드 향수만 반환")
        fun searchByBrand() {
            // given
            val request =
                RagProductSearchRequest(
                    brand = "Chanel",
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.totalCount", org.hamcrest.Matchers.greaterThanOrEqualTo(2)))
                .andExpect(jsonPath("$.results[*].brand").value(org.hamcrest.Matchers.everyItem(org.hamcrest.Matchers.`is`("Chanel"))))
        }

        @Test
        @DisplayName("gender로 검색 시 해당 성별 향수만 반환")
        fun searchByGender() {
            // given
            val request =
                RagProductSearchRequest(
                    gender = "Female",
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.totalCount", org.hamcrest.Matchers.greaterThanOrEqualTo(3)))
                .andExpect(jsonPath("$.results[*].gender").value(org.hamcrest.Matchers.everyItem(org.hamcrest.Matchers.`is`("Female"))))
        }

        @Test
        @DisplayName("필터 없이 검색 시 모든 향수 반환")
        fun searchWithoutFilter() {
            // given
            val request = RagProductSearchRequest()

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.totalCount", org.hamcrest.Matchers.greaterThanOrEqualTo(5)))
                .andExpect(jsonPath("$.results").isArray)
        }

        @Test
        @DisplayName("응답 DTO 필드 검증 (mainAccords, sizes 등)")
        fun verifyResponseFields() {
            // given
            val request =
                RagProductSearchRequest(
                    productIds = listOf(PERFUME_CHANEL_COCO),
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.results[0].id").value(PERFUME_CHANEL_COCO.toString()))
                .andExpect(jsonPath("$.results[0].brand").value("Chanel"))
                .andExpect(jsonPath("$.results[0].name").value("Coco Mademoiselle_TEST"))
                .andExpect(jsonPath("$.results[0].concentration").value("Eau de Parfum"))
                .andExpect(jsonPath("$.results[0].gender").value("Female"))
                .andExpect(jsonPath("$.results[0].mainAccords").isArray)
                .andExpect(jsonPath("$.results[0].sizes").isArray)
                .andExpect(jsonPath("$.results[0].sizes[0].sizeMl").value(50))
                .andExpect(jsonPath("$.results[0].sizes[0].price").value(200000))
        }

        @Test
        @DisplayName("노트 한국어 변환 검증")
        fun verifyKoreanTranslation() {
            // given
            val request =
                RagProductSearchRequest(
                    productIds = listOf(PERFUME_CHANEL_COCO),
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                // mainAccords는 NoteTranslator에 의해 한국어로 변환됨
                .andExpect(jsonPath("$.results[0].mainAccords").isArray)
                // 영어에서 한국어로 변환된 값 확인 (Floral -> 플로랄)
                .andExpect(jsonPath("$.results[0].mainAccords[0]").value("플로랄"))
        }
    }

    @Nested
    @DisplayName("GET /internal/v1/rag/perfumes/{id} - 향수 상세 조회")
    inner class GetPerfumeDetail {
        @Test
        @DisplayName("존재하는 향수 상세 조회 성공")
        fun getExistingPerfume() {
            // when & then
            mockMvc
                .perform(get("/internal/v1/rag/perfumes/$PERFUME_DIOR_SAUVAGE"))
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.id").value(PERFUME_DIOR_SAUVAGE.toString()))
                .andExpect(jsonPath("$.brand").value("Dior"))
                .andExpect(jsonPath("$.name").value("Sauvage_TEST"))
                .andExpect(jsonPath("$.concentration").value("Eau de Parfum"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.mainAccords").isArray)
                .andExpect(jsonPath("$.topNotes").isArray)
                .andExpect(jsonPath("$.middleNotes").isArray)
                .andExpect(jsonPath("$.baseNotes").isArray)
                .andExpect(jsonPath("$.seasonScore").exists())
                .andExpect(jsonPath("$.seasonScore.spring").value(55.0))
                .andExpect(jsonPath("$.dayNightScore").exists())
                .andExpect(jsonPath("$.dayNightScore.day").value(80.0))
                .andExpect(jsonPath("$.sizes").isArray)
                .andExpect(jsonPath("$.detailUrl").value("https://example.com/dior-sauvage"))
        }

        @Test
        @DisplayName("존재하지 않는 향수 조회 시 404 반환")
        fun getNotExistingPerfume() {
            // given
            val nonExistentId = UUID.randomUUID()

            // when & then
            mockMvc
                .perform(get("/internal/v1/rag/perfumes/$nonExistentId"))
                .andExpect(status().isNotFound)
        }
    }

    @Nested
    @DisplayName("POST /internal/v1/rag/perfumes/compare - 향수 비교")
    inner class ComparePerfumes {
        @Test
        @DisplayName("2개 향수 비교 성공")
        fun compareTwoPerfumes() {
            // given
            val request =
                RagCompareProductsRequest(
                    productIds = listOf(PERFUME_CHANEL_COCO, PERFUME_DIOR_MISS),
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.comparisonCount").value(2))
                .andExpect(jsonPath("$.products").isArray)
                .andExpect(jsonPath("$.products.length()").value(2))
        }

        @Test
        @DisplayName("4개 향수 비교 성공")
        fun compareFourPerfumes() {
            // given
            val request =
                RagCompareProductsRequest(
                    productIds =
                        listOf(
                            PERFUME_CHANEL_COCO,
                            PERFUME_DIOR_MISS,
                            PERFUME_DIOR_SAUVAGE,
                            PERFUME_JOMALONE,
                        ),
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.comparisonCount").value(4))
                .andExpect(jsonPath("$.products.length()").value(4))
        }

        @Test
        @DisplayName("비교 응답 DTO 필드 검증")
        fun verifyCompareResponseFields() {
            // given
            val request =
                RagCompareProductsRequest(
                    productIds = listOf(PERFUME_JOMALONE, PERFUME_CHANEL_NO5),
                )

            // when & then
            mockMvc
                .perform(
                    post("/internal/v1/rag/perfumes/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)),
                ).andDo(print())
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.comparisonCount").value(2))
                .andExpect(jsonPath("$.products[0].id").exists())
                .andExpect(jsonPath("$.products[0].brand").exists())
                .andExpect(jsonPath("$.products[0].name").exists())
                .andExpect(jsonPath("$.products[0].concentration").exists())
                .andExpect(jsonPath("$.products[0].mainAccords").isArray)
                .andExpect(jsonPath("$.products[0].topNotes").isArray)
                .andExpect(jsonPath("$.products[0].middleNotes").isArray)
                .andExpect(jsonPath("$.products[0].baseNotes").isArray)
                .andExpect(jsonPath("$.products[0].gender").exists())
                .andExpect(jsonPath("$.products[0].sizes").isArray)
        }
    }
}

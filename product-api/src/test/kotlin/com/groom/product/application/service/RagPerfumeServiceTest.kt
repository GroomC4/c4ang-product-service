package com.groom.product.application.service

import com.groom.product.common.annotation.UnitTest
import com.groom.product.common.exception.ProductException
import com.groom.product.common.fixture.PerfumeTestFixture
import com.groom.product.domain.port.LoadPerfumePort
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.UUID

@UnitTest
class RagPerfumeServiceTest :
    BehaviorSpec({

        Given("향수 검색 (searchPerfumes)") {

            When("productIds가 제공된 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val productId1 = UUID.randomUUID()
                val productId2 = UUID.randomUUID()
                val perfume1 = PerfumeTestFixture.createPerfume(productId = productId1)
                val perfume2 = PerfumeTestFixture.createPerfume(productId = productId2)

                every { loadPerfumePort.loadAllById(listOf(productId1, productId2)) } returns listOf(perfume1, perfume2)

                Then("productIds로 향수를 조회하고 다른 필터는 무시한다") {
                    val result =
                        ragPerfumeService.searchPerfumes(
                            productIds = listOf(productId1, productId2),
                            brand = "ignored",
                            gender = "ignored",
                        )

                    result shouldHaveSize 2
                    verify { loadPerfumePort.loadAllById(listOf(productId1, productId2)) }
                    verify(exactly = 0) { loadPerfumePort.loadByBrand(any()) }
                    verify(exactly = 0) { loadPerfumePort.loadByGender(any()) }
                }
            }

            When("빈 productIds가 제공된 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfume = PerfumeTestFixture.createPerfume(brand = "Chanel")

                every { loadPerfumePort.loadByBrand("Chanel") } returns listOf(perfume)

                Then("brand 필터로 향수를 조회한다") {
                    val result =
                        ragPerfumeService.searchPerfumes(
                            productIds = emptyList(),
                            brand = "Chanel",
                            gender = null,
                        )

                    result shouldHaveSize 1
                    result[0].brand shouldBe "Chanel"
                    verify { loadPerfumePort.loadByBrand("Chanel") }
                }
            }

            When("brand 필터만 제공된 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfume1 = PerfumeTestFixture.createPerfume(brand = "Dior")
                val perfume2 = PerfumeTestFixture.createPerfume(brand = "Dior")

                every { loadPerfumePort.loadByBrand("Dior") } returns listOf(perfume1, perfume2)

                Then("brand로 향수를 조회한다") {
                    val result =
                        ragPerfumeService.searchPerfumes(
                            productIds = null,
                            brand = "Dior",
                            gender = null,
                        )

                    result shouldHaveSize 2
                    result.all { it.brand == "Dior" } shouldBe true
                    verify { loadPerfumePort.loadByBrand("Dior") }
                }
            }

            When("gender 필터만 제공된 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val femalePerfume1 = PerfumeTestFixture.createFemalePerfume()
                val femalePerfume2 = PerfumeTestFixture.createFemalePerfume()

                every { loadPerfumePort.loadByGender("Female") } returns listOf(femalePerfume1, femalePerfume2)

                Then("gender로 향수를 조회한다") {
                    val result =
                        ragPerfumeService.searchPerfumes(
                            productIds = null,
                            brand = null,
                            gender = "Female",
                        )

                    result shouldHaveSize 2
                    result.all { it.gender == "Female" } shouldBe true
                    verify { loadPerfumePort.loadByGender("Female") }
                }
            }

            When("필터가 제공되지 않은 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val allPerfumes =
                    listOf(
                        PerfumeTestFixture.createFemalePerfume(),
                        PerfumeTestFixture.createMalePerfume(),
                        PerfumeTestFixture.createUnisexPerfume(),
                    )

                every { loadPerfumePort.loadAll() } returns allPerfumes

                Then("모든 향수를 조회한다") {
                    val result =
                        ragPerfumeService.searchPerfumes(
                            productIds = null,
                            brand = null,
                            gender = null,
                        )

                    result shouldHaveSize 3
                    verify { loadPerfumePort.loadAll() }
                }
            }

            When("brand가 빈 문자열인 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfume = PerfumeTestFixture.createMalePerfume()

                every { loadPerfumePort.loadByGender("Male") } returns listOf(perfume)

                Then("빈 brand는 무시하고 gender로 조회한다") {
                    val result =
                        ragPerfumeService.searchPerfumes(
                            productIds = null,
                            brand = "  ",
                            gender = "Male",
                        )

                    result shouldHaveSize 1
                    verify { loadPerfumePort.loadByGender("Male") }
                    verify(exactly = 0) { loadPerfumePort.loadByBrand(any()) }
                }
            }
        }

        Given("향수 상세 조회 (getPerfumeDetail)") {

            When("존재하는 향수 ID로 조회하는 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfumeId = UUID.randomUUID()
                val perfume = PerfumeTestFixture.createPerfume(id = perfumeId, brand = "Creed", name = "Aventus")

                every { loadPerfumePort.loadById(perfumeId) } returns perfume

                Then("향수 상세 정보를 반환한다") {
                    val result = ragPerfumeService.getPerfumeDetail(perfumeId)

                    result.id shouldBe perfumeId
                    result.brand shouldBe "Creed"
                    result.name shouldBe "Aventus"
                    verify { loadPerfumePort.loadById(perfumeId) }
                }
            }

            When("존재하지 않는 향수 ID로 조회하는 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val nonExistentId = UUID.randomUUID()

                every { loadPerfumePort.loadById(nonExistentId) } returns null

                Then("ProductNotFound 예외를 던진다") {
                    val exception =
                        shouldThrow<ProductException.ProductNotFound> {
                            ragPerfumeService.getPerfumeDetail(nonExistentId)
                        }

                    exception.productId shouldBe nonExistentId
                    verify { loadPerfumePort.loadById(nonExistentId) }
                }
            }
        }

        Given("향수 비교 (comparePerfumes)") {

            When("2개의 향수를 비교하는 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfumeId1 = UUID.randomUUID()
                val perfumeId2 = UUID.randomUUID()
                val perfume1 = PerfumeTestFixture.createFemalePerfume(id = perfumeId1)
                val perfume2 = PerfumeTestFixture.createMalePerfume(id = perfumeId2)

                every { loadPerfumePort.loadAllById(listOf(perfumeId1, perfumeId2)) } returns listOf(perfume1, perfume2)

                Then("2개의 향수 정보를 반환한다") {
                    val result = ragPerfumeService.comparePerfumes(listOf(perfumeId1, perfumeId2))

                    result shouldHaveSize 2
                    result.map { it.id } shouldContainExactly listOf(perfumeId1, perfumeId2)
                    verify { loadPerfumePort.loadAllById(listOf(perfumeId1, perfumeId2)) }
                }
            }

            When("4개의 향수를 비교하는 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfumeIds = (1..4).map { UUID.randomUUID() }
                val perfumes = perfumeIds.map { PerfumeTestFixture.createPerfume(id = it) }

                every { loadPerfumePort.loadAllById(perfumeIds) } returns perfumes

                Then("4개의 향수 정보를 반환한다") {
                    val result = ragPerfumeService.comparePerfumes(perfumeIds)

                    result shouldHaveSize 4
                    verify { loadPerfumePort.loadAllById(perfumeIds) }
                }
            }

            When("1개의 향수만 비교하려는 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfumeId = UUID.randomUUID()

                Then("IllegalArgumentException 예외를 던진다") {
                    shouldThrow<IllegalArgumentException> {
                        ragPerfumeService.comparePerfumes(listOf(perfumeId))
                    }
                }
            }

            When("5개 이상의 향수를 비교하려는 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                val perfumeIds = (1..5).map { UUID.randomUUID() }

                Then("IllegalArgumentException 예외를 던진다") {
                    shouldThrow<IllegalArgumentException> {
                        ragPerfumeService.comparePerfumes(perfumeIds)
                    }
                }
            }

            When("빈 목록으로 비교하려는 경우") {
                val loadPerfumePort = mockk<LoadPerfumePort>()
                val ragPerfumeService = RagPerfumeService(loadPerfumePort)

                Then("IllegalArgumentException 예외를 던진다") {
                    shouldThrow<IllegalArgumentException> {
                        ragPerfumeService.comparePerfumes(emptyList())
                    }
                }
            }
        }
    })

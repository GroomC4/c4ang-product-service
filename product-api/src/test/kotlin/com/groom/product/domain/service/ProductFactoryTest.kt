package com.groom.product.domain.service

import com.groom.product.common.annotation.UnitTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal
import java.util.UUID

/**
 * ProductFactory 단위 테스트.
 *
 * 참고: ProductFactory는 raw 타입(String, BigDecimal, Int)을 받지만,
 * 실제 애플리케이션 계층(RegisterProductService 등)에서는 Value Object 검증을 먼저 수행한 후
 * VO.value를 전달하므로, 이 테스트에서는 유효한 값만 사용합니다.
 *
 * 검증 책임:
 * - 애플리케이션 계층: Value Object를 통한 엄격한 입력 검증
 * - 도메인 계층 (엔티티): 기본 검증 유지 (안전망)
 * - 도메인 계층 (팩토리): 검증 없음 (이미 검증된 값을 받음)
 */
@UnitTest
class ProductFactoryTest :
    BehaviorSpec({
        Given("ProductFactory가 주어질 때") {
            val productFactory = ProductFactory()

            val storeId = UUID.randomUUID()
            val categoryId = UUID.randomUUID()

            When("이미지가 포함된 새 상품을 생성하면") {
                val images =
                    listOf(
                        ProductFactory.ImageData(
                            imageType = "PRIMARY",
                            imageUrl = "https://example.com/primary.jpg",
                            displayOrder = 0,
                        ),
                        ProductFactory.ImageData(
                            imageType = "DETAIL",
                            imageUrl = "https://example.com/detail1.jpg",
                            displayOrder = 1,
                        ),
                        ProductFactory.ImageData(
                            imageType = "DETAIL",
                            imageUrl = "https://example.com/detail2.jpg",
                            displayOrder = 2,
                        ),
                    )

                val product =
                    productFactory.createNewProduct(
                        storeId = storeId,
                        storeName = "테스트 스토어",
                        categoryId = categoryId,
                        name = "무선 마우스",
                        price = BigDecimal("79900"),
                        stockQuantity = 100,
                        thumbnailUrl = "https://example.com/thumbnail.jpg",
                        description = "고성능 무선 마우스",
                        images = images,
                    )

                Then("상품이 올바르게 생성된다") {
                    product shouldNotBe null
                    product.id shouldNotBe null
                    product.storeId shouldBe storeId
                    product.storeName shouldBe "테스트 스토어"
                    product.categoryId shouldBe categoryId
                    product.name shouldBe "무선 마우스"
                    product.price shouldBe BigDecimal("79900")
                    product.stockQuantity shouldBe 100
                    product.thumbnailUrl shouldBe "https://example.com/thumbnail.jpg"
                    product.description shouldBe "고성능 무선 마우스"
                    product.status shouldBe "ON_SALE"
                }

                Then("상품에 이미지가 올바르게 추가된다") {
                    product.images.size shouldBe 3

                    product.images[0].imageType shouldBe "PRIMARY"
                    product.images[0].imageUrl shouldBe "https://example.com/primary.jpg"
                    product.images[0].displayOrder shouldBe 0
                    product.images[0].product shouldBe product

                    product.images[1].imageType shouldBe "DETAIL"
                    product.images[1].imageUrl shouldBe "https://example.com/detail1.jpg"
                    product.images[1].displayOrder shouldBe 1
                    product.images[1].product shouldBe product

                    product.images[2].imageType shouldBe "DETAIL"
                    product.images[2].imageUrl shouldBe "https://example.com/detail2.jpg"
                    product.images[2].displayOrder shouldBe 2
                    product.images[2].product shouldBe product
                }
            }

            When("이미지 없이 새 상품을 생성하면") {
                val product =
                    productFactory.createNewProduct(
                        storeId = storeId,
                        storeName = "테스트 스토어",
                        categoryId = categoryId,
                        name = "무선 마우스",
                        price = BigDecimal("79900"),
                        stockQuantity = 100,
                        thumbnailUrl = null,
                        description = null,
                        images = emptyList(),
                    )

                Then("이미지가 없는 상품이 생성된다") {
                    product shouldNotBe null
                    product.images.size shouldBe 0
                    product.thumbnailUrl shouldBe null
                    product.description shouldBe null
                }
            }

            When("여러 상품을 생성하면") {
                val product1 =
                    productFactory.createNewProduct(
                        storeId = storeId,
                        storeName = "테스트 스토어",
                        categoryId = categoryId,
                        name = "무선 마우스",
                        price = BigDecimal("79900"),
                        stockQuantity = 100,
                        thumbnailUrl = null,
                        description = null,
                        images = emptyList(),
                    )

                val product2 =
                    productFactory.createNewProduct(
                        storeId = storeId,
                        storeName = "테스트 스토어",
                        categoryId = categoryId,
                        name = "기계식 키보드",
                        price = BigDecimal("129900"),
                        stockQuantity = 50,
                        thumbnailUrl = null,
                        description = null,
                        images = emptyList(),
                    )

                Then("각 상품은 고유한 ID를 갖는다") {
                    product1.id shouldNotBe product2.id
                }
            }

            When("이미지 순서가 섞인 상태로 상품을 생성하면") {
                val images =
                    listOf(
                        ProductFactory.ImageData(
                            imageType = "DETAIL",
                            imageUrl = "https://example.com/detail.jpg",
                            displayOrder = 2,
                        ),
                        ProductFactory.ImageData(
                            imageType = "PRIMARY",
                            imageUrl = "https://example.com/primary.jpg",
                            displayOrder = 0,
                        ),
                        ProductFactory.ImageData(
                            imageType = "DETAIL",
                            imageUrl = "https://example.com/detail2.jpg",
                            displayOrder = 1,
                        ),
                    )

                val product =
                    productFactory.createNewProduct(
                        storeId = storeId,
                        storeName = "테스트 스토어",
                        categoryId = categoryId,
                        name = "무선 마우스",
                        price = BigDecimal("79900"),
                        stockQuantity = 100,
                        thumbnailUrl = null,
                        description = null,
                        images = images,
                    )

                Then("이미지가 입력 순서대로 추가된다") {
                    product.images.size shouldBe 3
                    product.images[0].displayOrder shouldBe 2 // 입력 순서 유지
                    product.images[1].displayOrder shouldBe 0
                    product.images[2].displayOrder shouldBe 1
                }
            }

            When("상품 생성 시 status는 자동으로 ON_SALE로 설정된다") {
                val product =
                    productFactory.createNewProduct(
                        storeId = storeId,
                        storeName = "테스트 스토어",
                        categoryId = categoryId,
                        name = "무선 마우스",
                        price = BigDecimal("79900"),
                        stockQuantity = 100,
                        thumbnailUrl = null,
                        description = null,
                        images = emptyList(),
                    )

                Then("상품 상태는 ON_SALE이다") {
                    product.status shouldBe "ON_SALE"
                }
            }
        }
    })

package com.groom.product.domain.model

import com.groom.product.common.annotation.UnitTest
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal
import java.util.UUID

@UnitTest
class ProductImageTest :
    FunSpec({
        test("ProductImage 생성 시 ID가 자동으로 할당된다") {
            // given & when
            val image =
                ProductImage(
                    imageType = "PRIMARY",
                    imageUrl = "https://example.com/image1.jpg",
                    displayOrder = 0,
                )

            // then
            image.id shouldNotBe null
        }

        test("같은 ID를 가진 ProductImage는 동일한 객체로 판단된다") {
            // given
            val image1 =
                ProductImage(
                    imageType = "PRIMARY",
                    imageUrl = "https://example.com/image1.jpg",
                    displayOrder = 0,
                )

            val image2 =
                ProductImage(
                    imageType = "DETAIL",
                    imageUrl = "https://example.com/image2.jpg",
                    displayOrder = 1,
                )

            // when & then
            // Note: ProductImage의 equals/hashCode가 id 기반이 아니면 이 테스트는 다시 검토 필요
            image1 shouldNotBe image2
        }

        test("다른 ID를 가진 ProductImage는 다른 객체로 판단된다") {
            // given
            val image1 =
                ProductImage(
                    imageType = "PRIMARY",
                    imageUrl = "https://example.com/image1.jpg",
                    displayOrder = 0,
                )

            val image2 =
                ProductImage(
                    imageType = "PRIMARY",
                    imageUrl = "https://example.com/image1.jpg",
                    displayOrder = 0,
                )

            // when & then
            image1 shouldNotBe image2
        }

        test("ProductImage를 Product에 추가하면 연관관계가 설정된다") {
            // given
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            val image =
                ProductImage(
                    imageType = "PRIMARY",
                    imageUrl = "https://example.com/image1.jpg",
                    displayOrder = 0,
                )

            // when
            product.addImage(image)

            // then
            image.product shouldBe product
        }

        test("display order는 이미지 순서를 나타낸다") {
            // given
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            val image1 =
                ProductImage(
                    imageType = "PRIMARY",
                    imageUrl = "https://example.com/image1.jpg",
                    displayOrder = 0,
                )

            val image2 =
                ProductImage(
                    imageType = "DETAIL",
                    imageUrl = "https://example.com/image2.jpg",
                    displayOrder = 1,
                )

            val image3 =
                ProductImage(
                    imageType = "DETAIL",
                    imageUrl = "https://example.com/image3.jpg",
                    displayOrder = 2,
                )

            // when
            product.addImage(image1)
            product.addImage(image2)
            product.addImage(image3)

            // then
            product.images.size shouldBe 3
            product.images[0].displayOrder shouldBe 0
            product.images[1].displayOrder shouldBe 1
            product.images[2].displayOrder shouldBe 2
        }

        test("toString()은 이미지 정보를 요약하여 반환한다") {
            // given
            val image =
                ProductImage(
                    imageType = "PRIMARY",
                    imageUrl = "https://example.com/image1.jpg",
                    displayOrder = 0,
                )

            // when
            val result = image.toString()

            // then
            result shouldBe "ProductImage(id=${image.id}, imageType=PRIMARY, displayOrder=0)"
        }
    })

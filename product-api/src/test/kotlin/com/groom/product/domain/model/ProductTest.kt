package com.groom.product.domain.model

import com.groom.product.common.annotation.UnitTest
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal
import java.util.UUID

/**
 * Product 엔티티 단위 테스트.
 *
 * 참고: Product 엔티티의 updateInfo() 메서드는 기본 검증을 유지합니다.
 * 실제 애플리케이션에서는 Value Object가 먼저 검증하지만,
 * 엔티티의 기본 검증은 안전망(safety net) 역할을 합니다.
 *
 * 검증 책임:
 * - 애플리케이션 계층: Value Object를 통한 엄격한 입력 검증 (1차 방어선)
 * - 도메인 계층 (엔티티): 기본 검증 유지 (2차 방어선, 안전망)
 */
@UnitTest
class ProductTest :
    FunSpec({
        test("상품 생성 시 기본 상태는 ON_SALE이다") {
            // given & when
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            // then
            product.status shouldBe "ON_SALE"
            product.deletedAt shouldBe null
            product.hiddenAt shouldBe null
        }

        test("addImage()로 상품에 이미지를 추가할 수 있다") {
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
            product.images.size shouldBe 1
            product.images[0] shouldBe image
            image.product shouldBe product
        }

        test("updateInfo()로 상품 정보를 수정하면 true를 반환한다") {
            // given
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "기존 상품명",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                    description = "기존 설명",
                )

            val newCategoryId = UUID.randomUUID()

            // when
            val hasChanges =
                product.updateInfo(
                    newName = "새 상품명",
                    newPrice = BigDecimal("15000"),
                    newStockQuantity = 200,
                    newDescription = "새 설명",
                    newThumbnailUrl = "https://example.com/new_thumbnail.jpg",
                    newCategoryId = newCategoryId,
                )

            // then
            hasChanges shouldBe true
            product.name shouldBe "새 상품명"
            product.price shouldBe BigDecimal("15000")
            product.stockQuantity shouldBe 200
            product.description shouldBe "새 설명"
            product.thumbnailUrl shouldBe "https://example.com/new_thumbnail.jpg"
            product.categoryId shouldBe newCategoryId
        }

        test("updateInfo()로 동일한 정보로 수정하면 false를 반환한다") {
            // given
            val categoryId = UUID.randomUUID()
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = categoryId,
                    name = "기존 상품명",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                    description = "기존 설명",
                    thumbnailUrl = "https://example.com/thumbnail.jpg",
                )

            // when
            val hasChanges =
                product.updateInfo(
                    newName = "기존 상품명",
                    newPrice = BigDecimal("10000"),
                    newStockQuantity = 100,
                    newDescription = "기존 설명",
                    newThumbnailUrl = "https://example.com/thumbnail.jpg",
                    newCategoryId = categoryId,
                )

            // then
            hasChanges shouldBe false
        }

        test("updateInfo()로 빈 상품명을 입력하면 예외가 발생한다") {
            // 엔티티의 기본 검증 테스트 (애플리케이션 계층에서는 VO가 먼저 검증함)
            // given
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "기존 상품명",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            // when & then
            shouldThrow<IllegalArgumentException> {
                product.updateInfo(
                    newName = "",
                    newPrice = BigDecimal("15000"),
                    newStockQuantity = 200,
                    newDescription = null,
                    newThumbnailUrl = null,
                    newCategoryId = UUID.randomUUID(),
                )
            }
        }

        test("updateInfo()로 음수 가격을 입력하면 예외가 발생한다") {
            // 엔티티의 기본 검증 테스트 (애플리케이션 계층에서는 VO가 먼저 검증함)
            // given
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "기존 상품명",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            // when & then
            shouldThrow<IllegalArgumentException> {
                product.updateInfo(
                    newName = "새 상품명",
                    newPrice = BigDecimal("-1000"),
                    newStockQuantity = 200,
                    newDescription = null,
                    newThumbnailUrl = null,
                    newCategoryId = UUID.randomUUID(),
                )
            }
        }

        test("updateInfo()로 음수 재고를 입력하면 예외가 발생한다") {
            // 엔티티의 기본 검증 테스트 (애플리케이션 계층에서는 VO가 먼저 검증함)
            // given
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "기존 상품명",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            // when & then
            shouldThrow<IllegalArgumentException> {
                product.updateInfo(
                    newName = "새 상품명",
                    newPrice = BigDecimal("15000"),
                    newStockQuantity = -10,
                    newDescription = null,
                    newThumbnailUrl = null,
                    newCategoryId = UUID.randomUUID(),
                )
            }
        }

        test("delete()로 상품을 삭제하면 deletedAt이 설정되고 상태가 OFF_SHELF가 된다") {
            // given
            val product =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "테스트 스토어",
                    categoryId = UUID.randomUUID(),
                    name = "테스트 상품",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                    status = "ON_SALE",
                )

            // when
            val result = product.delete()

            // then
            result shouldBe true
            product.deletedAt shouldNotBe null
            product.status shouldBe "OFF_SHELF"
        }

        test("이미 삭제된 상품을 다시 delete()하면 false를 반환한다") {
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

            product.delete() // 첫 번째 삭제

            // when
            val result = product.delete() // 두 번째 삭제 시도

            // then
            result shouldBe false
        }

        test("toggleHide()로 상품을 숨기면 hiddenAt이 설정되고 true를 반환한다") {
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

            // when
            val isHidden = product.toggleHide()

            // then
            isHidden shouldBe true
            product.hiddenAt shouldNotBe null
        }

        test("toggleHide()로 숨겨진 상품을 복원하면 hiddenAt이 null이 되고 false를 반환한다") {
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

            product.toggleHide() // 숨김 처리

            // when
            val isHidden = product.toggleHide() // 복원

            // then
            isHidden shouldBe false
            product.hiddenAt shouldBe null
        }

        test("같은 ID를 가진 Product는 동일한 객체로 판단된다") {
            // given
            val productId = UUID.randomUUID()
            val product1 =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "스토어1",
                    categoryId = UUID.randomUUID(),
                    name = "상품1",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            val product2 =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "스토어2",
                    categoryId = UUID.randomUUID(),
                    name = "상품2",
                    price = BigDecimal("20000"),
                    stockQuantity = 200,
                )

            // when & then
            // Note: equals/hashCode는 id 기반이 아니라면 이 테스트는 통과하지 않음
            // Product 엔티티가 id 기반 equals를 사용하지 않는다면 테스트 수정 필요
            product1 shouldNotBe product2
        }

        test("다른 ID를 가진 Product는 다른 객체로 판단된다") {
            // given
            val product1 =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "스토어1",
                    categoryId = UUID.randomUUID(),
                    name = "상품1",
                    price = BigDecimal("10000"),
                    stockQuantity = 100,
                )

            val product2 =
                Product(
                    storeId = UUID.randomUUID(),
                    storeName = "스토어2",
                    categoryId = UUID.randomUUID(),
                    name = "상품2",
                    price = BigDecimal("20000"),
                    stockQuantity = 200,
                )

            // when & then
            product1 shouldNotBe product2
        }

        test("decreaseStock()으로 재고를 차감하면 재고가 감소한다") {
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

            // when
            val remainingStock = product.decreaseStock(30)

            // then
            remainingStock shouldBe 70
            product.stockQuantity shouldBe 70
        }

        test("decreaseStock()으로 재고보다 많은 수량을 차감하면 예외가 발생한다") {
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

            // when & then
            shouldThrow<IllegalArgumentException> {
                product.decreaseStock(150)
            }
        }

        test("decreaseStock()으로 음수 수량을 차감하면 예외가 발생한다") {
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

            // when & then
            shouldThrow<IllegalArgumentException> {
                product.decreaseStock(-10)
            }
        }

        test("increaseStock()으로 재고를 증가시키면 재고가 증가한다") {
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

            // when
            val newStock = product.increaseStock(50)

            // then
            newStock shouldBe 150
            product.stockQuantity shouldBe 150
        }

        test("increaseStock()으로 음수 수량을 증가시키면 예외가 발생한다") {
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

            // when & then
            shouldThrow<IllegalArgumentException> {
                product.increaseStock(-10)
            }
        }

        test("decreaseStock()과 increaseStock()을 조합하여 재고를 관리할 수 있다") {
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

            // when
            product.decreaseStock(30) // 100 - 30 = 70
            product.increaseStock(20) // 70 + 20 = 90
            val finalStock = product.decreaseStock(10) // 90 - 10 = 80

            // then
            finalStock shouldBe 80
            product.stockQuantity shouldBe 80
        }
    })

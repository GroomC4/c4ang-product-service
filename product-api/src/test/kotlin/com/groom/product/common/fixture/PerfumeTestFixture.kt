package com.groom.product.common.fixture

import com.groom.product.domain.model.Perfume
import java.time.LocalDateTime
import java.util.UUID

/**
 * Perfume 엔티티 테스트 픽스처
 *
 * JPA Auditing으로 설정되는 createdAt/updatedAt 같은 필드를
 * 리플렉션으로 초기화하여 테스트용 Perfume 객체를 생성합니다.
 */
object PerfumeTestFixture {
    /**
     * 기본 Perfume 생성
     */
    fun createPerfume(
        id: UUID = UUID.randomUUID(),
        productId: UUID = UUID.randomUUID(),
        brand: String = "Test Brand",
        name: String = "Test Perfume",
        concentration: String? = "Eau de Parfum",
        mainAccords: String? = """["Floral", "Fruity"]""",
        topNotes: String? = """["Bergamot", "Lemon"]""",
        middleNotes: String? = """["Rose", "Jasmine"]""",
        baseNotes: String? = """["Musk", "Sandalwood"]""",
        notesScore: String? = """{"floral": 80.0, "fruity": 70.0}""",
        seasonScore: String? = """{"spring": 75.0, "summer": 60.0, "fall": 45.0, "winter": 30.0}""",
        dayNightScore: String? = """{"day": 80.0, "night": 50.0}""",
        gender: String? = "Unisex",
        sizes: String? = """[{"sizeMl": 50, "price": 150000}, {"sizeMl": 100, "price": 250000}]""",
        detailUrl: String? = "https://example.com/perfume/test",
        deletedAt: LocalDateTime? = null,
        createdAt: LocalDateTime = LocalDateTime.now(),
        updatedAt: LocalDateTime = LocalDateTime.now(),
    ): Perfume {
        val perfume =
            Perfume(
                id = id,
                productId = productId,
                brand = brand,
                name = name,
                concentration = concentration,
                mainAccords = mainAccords,
                topNotes = topNotes,
                middleNotes = middleNotes,
                baseNotes = baseNotes,
                notesScore = notesScore,
                seasonScore = seasonScore,
                dayNightScore = dayNightScore,
                gender = gender,
                sizes = sizes,
                detailUrl = detailUrl,
                deletedAt = deletedAt,
            )

        // 리플렉션으로 protected 필드 설정
        ProductTestFixture.setField(perfume, "createdAt", createdAt)
        ProductTestFixture.setField(perfume, "updatedAt", updatedAt)

        return perfume
    }

    /**
     * 여성 향수 생성
     */
    fun createFemalePerfume(
        id: UUID = UUID.randomUUID(),
        productId: UUID = UUID.randomUUID(),
        brand: String = "Chanel",
        name: String = "Coco Mademoiselle",
    ): Perfume =
        createPerfume(
            id = id,
            productId = productId,
            brand = brand,
            name = name,
            gender = "Female",
            mainAccords = """["Floral", "Citrus", "Fresh"]""",
        )

    /**
     * 남성 향수 생성
     */
    fun createMalePerfume(
        id: UUID = UUID.randomUUID(),
        productId: UUID = UUID.randomUUID(),
        brand: String = "Dior",
        name: String = "Sauvage",
    ): Perfume =
        createPerfume(
            id = id,
            productId = productId,
            brand = brand,
            name = name,
            gender = "Male",
            mainAccords = """["Woody", "Aromatic", "Fresh Spicy"]""",
        )

    /**
     * 유니섹스 향수 생성
     */
    fun createUnisexPerfume(
        id: UUID = UUID.randomUUID(),
        productId: UUID = UUID.randomUUID(),
        brand: String = "Jo Malone",
        name: String = "Lime Basil & Mandarin",
    ): Perfume =
        createPerfume(
            id = id,
            productId = productId,
            brand = brand,
            name = name,
            gender = "Unisex",
            mainAccords = """["Citrus", "Aromatic", "Green"]""",
        )

    /**
     * 삭제된 향수 생성
     */
    fun createDeletedPerfume(
        id: UUID = UUID.randomUUID(),
        productId: UUID = UUID.randomUUID(),
        brand: String = "Deleted Brand",
        name: String = "Deleted Perfume",
    ): Perfume =
        createPerfume(
            id = id,
            productId = productId,
            brand = brand,
            name = name,
            deletedAt = LocalDateTime.now(),
        )
}

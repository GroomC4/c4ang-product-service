package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.Perfume
import com.groom.product.domain.port.LoadPerfumePort
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * Perfume 영속성 Adapter.
 *
 * LoadPerfumePort를 구현하여 Domain Layer와 JPA Repository를 연결합니다.
 */
@Component
class PerfumePersistenceAdapter(
    private val perfumeJpaRepository: PerfumeJpaRepository,
) : LoadPerfumePort {
    override fun loadById(id: UUID): Perfume? = perfumeJpaRepository.findById(id).orElse(null)

    override fun loadByProductId(productId: UUID): Perfume? = perfumeJpaRepository.findByProductId(productId).orElse(null)

    override fun loadAllById(ids: Iterable<UUID>): List<Perfume> = perfumeJpaRepository.findAllById(ids)

    override fun loadAllByProductIds(productIds: Iterable<UUID>): List<Perfume> =
        perfumeJpaRepository.findAllByProductIdIn(productIds.toList())

    override fun loadByBrand(brand: String): List<Perfume> = perfumeJpaRepository.findByBrand(brand)

    override fun loadByGender(gender: String): List<Perfume> = perfumeJpaRepository.findByGender(gender)

    override fun loadAll(): List<Perfume> = perfumeJpaRepository.findAllByDeletedAtIsNull()
}

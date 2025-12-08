package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.Perfume
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.Optional
import java.util.UUID

/**
 * Perfume JPA Repository (Adapter 내부용).
 *
 * 이 인터페이스는 Persistence Adapter 내부에서만 사용됩니다.
 * Domain Layer는 이 인터페이스를 직접 의존하지 않고, Port를 통해 접근합니다.
 */
interface PerfumeJpaRepository :
    JpaRepository<Perfume, UUID>,
    JpaSpecificationExecutor<Perfume> {
    fun findByProductId(productId: UUID): Optional<Perfume>

    fun findAllByProductIdIn(productIds: Collection<UUID>): List<Perfume>

    fun findByBrand(brand: String): List<Perfume>

    fun findByGender(gender: String): List<Perfume>

    fun findByBrandAndName(
        brand: String,
        name: String,
    ): Optional<Perfume>

    fun findAllByDeletedAtIsNull(): List<Perfume>
}

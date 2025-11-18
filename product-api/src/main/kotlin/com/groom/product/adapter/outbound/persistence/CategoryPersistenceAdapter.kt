package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.ProductCategory
import com.groom.product.domain.port.LoadCategoryPort
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * Category 영속성 Adapter.
 *
 * LoadCategoryPort를 구현하여 Domain Layer와 JPA Repository를 연결합니다.
 */
@Component
class CategoryPersistenceAdapter(
    private val categoryJpaRepository: CategoryJpaRepository,
) : LoadCategoryPort {
    override fun loadById(id: UUID): ProductCategory? = categoryJpaRepository.findById(id).orElse(null)

    override fun loadAncestorCategoriesById(id: UUID): List<ProductCategory> =
        categoryJpaRepository.findAncestorCategoriesById(id)
}

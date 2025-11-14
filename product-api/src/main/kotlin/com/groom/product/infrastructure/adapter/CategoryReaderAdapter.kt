package com.groom.product.infrastructure.adapter

import com.groom.product.domain.model.ProductCategory
import com.groom.product.domain.port.LoadCategoryPort
import com.groom.product.infrastructure.repository.ProductCategoryRepositoryImpl
import org.springframework.stereotype.Component
import java.util.Optional
import java.util.UUID

/**
 * CategoryReader Port의 Adapter 구현체.
 *
 * Domain Layer가 Infrastructure Layer에 의존하지 않도록
 * Port-Adapter 패턴을 통해 의존성을 역전시킵니다.
 */
@Component
class CategoryReaderAdapter(
    private val categoryRepository: ProductCategoryRepositoryImpl,
) : CategoryReader {
    override fun findById(categoryId: UUID): Optional<ProductCategory> = categoryRepository.findById(categoryId)

    override fun findAncestorCategoriesById(categoryId: UUID): List<ProductCategory> =
        categoryRepository.findAncestorCategoriesById(categoryId)
}

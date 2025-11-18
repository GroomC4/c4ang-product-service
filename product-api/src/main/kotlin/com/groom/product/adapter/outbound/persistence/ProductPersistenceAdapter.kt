package com.groom.product.adapter.out.persistence

import com.groom.product.domain.model.Product
import com.groom.product.domain.port.LoadProductPort
import com.groom.product.domain.port.SaveProductPort
import com.groom.product.domain.port.SearchProductsPort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * Product 영속성 Adapter.
 *
 * LoadProductPort, SaveProductPort, SearchProductsPort를 구현하여
 * Domain Layer와 JPA Repository를 연결합니다.
 */
@Component
class ProductPersistenceAdapter(
    private val productJpaRepository: ProductJpaRepository,
) : LoadProductPort,
    SaveProductPort,
    SearchProductsPort {
    override fun loadById(id: UUID): Product? = productJpaRepository.findById(id).orElse(null)

    override fun loadByIdWithImages(id: UUID): Product? = productJpaRepository.findByIdWithImages(id).orElse(null)

    override fun loadAllById(ids: Iterable<UUID>): List<Product> = productJpaRepository.findAllById(ids)

    override fun loadByStoreId(storeId: UUID): List<Product> = productJpaRepository.findByStoreId(storeId)

    override fun existsByName(name: String): Boolean = productJpaRepository.existsByName(name)

    override fun save(product: Product): Product = productJpaRepository.save(product)

    override fun saveAll(products: Iterable<Product>): List<Product> = productJpaRepository.saveAll(products)

    override fun search(
        spec: Specification<Product>,
        pageable: Pageable,
    ): Page<Product> = productJpaRepository.findAll(spec, pageable)
}

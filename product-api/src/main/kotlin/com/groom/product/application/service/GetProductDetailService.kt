package com.groom.product.application.service

import com.groom.product.application.dto.GetProductDetailQuery
import com.groom.product.application.dto.GetProductDetailResult
import com.groom.product.domain.port.LoadProductPort
import com.groom.product.domain.service.CategoryPathBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetProductDetailService(
    private val loadProductPort: LoadProductPort,
    private val categoryPathBuilder: CategoryPathBuilder,
) {
    @Transactional(readOnly = true)
    fun getProductDetail(query: GetProductDetailQuery): GetProductDetailResult {
        val product =
            loadProductPort.loadByIdWithImages(query.productId)
                ?: throw IllegalArgumentException("Product not found: ${query.productId}")

        val categoryPath =
            product.categoryId.let {
                categoryPathBuilder.buildPath(it)
            }

        return GetProductDetailResult.from(product, categoryPath)
    }
}

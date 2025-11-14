package com.groom.product.domain.service

import com.groom.product.domain.port.LoadCategoryPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CategoryPathBuilder(
    private val loadCategoryPort: LoadCategoryPort,
) {
    fun buildPath(categoryId: UUID): String {
        val categories = loadCategoryPort.loadAncestorCategoriesById(categoryId)
        return categories.joinToString(" > ") { it.name }
    }
}

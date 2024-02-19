package com.a03.concurrencycontrolproject.domain.category.dto

import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.goods.model.Goods

data class CategoryResponse(
    val id: Long,
    val title: String,

) {
    companion object {
        fun from(category: Category): CategoryResponse {
            return CategoryResponse(
                id = category.id!!,
                title = category.title,
            )
        }
    }
}
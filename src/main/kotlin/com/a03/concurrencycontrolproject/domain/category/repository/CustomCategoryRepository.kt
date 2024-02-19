package com.a03.concurrencycontrolproject.domain.category.repository

import com.a03.concurrencycontrolproject.domain.category.model.Category
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomCategoryRepository {

    fun findByPageable(pageable: Pageable): Page<Category>
}
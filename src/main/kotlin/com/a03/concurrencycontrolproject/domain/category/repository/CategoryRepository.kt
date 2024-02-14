package com.a03.concurrencycontrolproject.domain.category.repository

import com.a03.concurrencycontrolproject.domain.category.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}
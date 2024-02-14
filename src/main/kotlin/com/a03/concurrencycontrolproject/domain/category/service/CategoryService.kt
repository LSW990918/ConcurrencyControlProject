package com.a03.concurrencycontrolproject.domain.category.service

import com.a03.concurrencycontrolproject.domain.category.dto.CategoryResponse
import com.a03.concurrencycontrolproject.domain.category.dto.CreateCategoryRequest
import com.a03.concurrencycontrolproject.domain.category.dto.UpdateCategoryRequest

interface CategoryService {

    fun createCategory(request: CreateCategoryRequest) : CategoryResponse

    fun updateCategory(categoryId: Long, request: UpdateCategoryRequest) : CategoryResponse

    fun deleteCategory(categoryId: Long)

    fun getCategoryList() : List<CategoryResponse>

    fun getCategory(categoryId: Long) : CategoryResponse
}
package com.a03.concurrencycontrolproject.domain.category.service

import com.a03.concurrencycontrolproject.domain.category.dto.CategoryResponse
import com.a03.concurrencycontrolproject.domain.category.dto.CategoryResponse.Companion.toResponse
import com.a03.concurrencycontrolproject.domain.category.dto.CreateCategoryRequest
import com.a03.concurrencycontrolproject.domain.category.dto.UpdateCategoryRequest
import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.category.repository.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    private val categoryRepository: CategoryRepository
) : CategoryService {

    override fun createCategory(request: CreateCategoryRequest) {
        val category = Category(
            title = request.title
        )
        categoryRepository.save(category)
    }

    override fun updateCategory(categoryId: Long, request: UpdateCategoryRequest){
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw Exception()
        category.title = request.title
    }

    override fun deleteCategory(categoryId: Long) {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw Exception()
        categoryRepository.delete(category)
    }

    override fun getCategoryList(): List<CategoryResponse> {
        val categoryList = categoryRepository.findAll()
        return categoryList.map { it.toResponse() }
    }

    override fun getCategory(categoryId: Long): CategoryResponse {
        val category = categoryRepository.findByIdOrNull(categoryId)
            ?: throw Exception()
        return category.toResponse()
    }

}
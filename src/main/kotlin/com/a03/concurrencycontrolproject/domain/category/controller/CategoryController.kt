package com.a03.concurrencycontrolproject.domain.category.controller

import com.a03.concurrencycontrolproject.domain.category.dto.CategoryResponse
import com.a03.concurrencycontrolproject.domain.category.dto.CreateCategoryRequest
import com.a03.concurrencycontrolproject.domain.category.dto.UpdateCategoryRequest
import com.a03.concurrencycontrolproject.domain.category.service.CategoryService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping
    fun createCategory(
        @Valid @RequestBody request: CreateCategoryRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(categoryService.createCategory(request))
    }

    @PutMapping("/{categoryId}")
    fun updateCategory(
        @PathVariable categoryId: Long,
        @Valid @RequestBody request: UpdateCategoryRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoryService.updateCategory(categoryId, request))
    }

    @DeleteMapping("/{categoryId}")
    fun deleteCategory(
        @PathVariable categoryId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(categoryService.deleteCategory(categoryId))
    }

    @GetMapping
    fun getCategoryList(): ResponseEntity<List<CategoryResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoryService.getCategoryList())
    }

    @GetMapping("/{categoryId}")
    fun getCategory(
        @PathVariable categoryId: Long
    ): ResponseEntity<CategoryResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoryService.getCategory(categoryId))
    }
}

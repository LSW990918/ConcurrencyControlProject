package com.a03.concurrencycontrolproject.domain.category.controller

import com.a03.concurrencycontrolproject.domain.category.dto.CategoryResponse
import com.a03.concurrencycontrolproject.domain.category.dto.CreateCategoryRequest
import com.a03.concurrencycontrolproject.domain.category.dto.UpdateCategoryRequest
import com.a03.concurrencycontrolproject.domain.category.service.CategoryService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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

    @Operation(summary = "카테고리 생성")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun createCategory(
        @Valid @RequestBody request: CreateCategoryRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(categoryService.createCategory(request))
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun updateCategory(
        @PathVariable categoryId: Long,
        @Valid @RequestBody request: UpdateCategoryRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoryService.updateCategory(categoryId, request))
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun deleteCategory(
        @PathVariable categoryId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(categoryService.deleteCategory(categoryId))
    }

    @Operation(summary = "카테고리 목록 조회")
    @GetMapping
    fun getCategoryList(): ResponseEntity<List<CategoryResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoryService.getCategoryList())
    }

    @Operation(summary = "카테고리 조회")
    @GetMapping("/{categoryId}")
    fun getCategory(
        @PathVariable categoryId: Long
    ): ResponseEntity<CategoryResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categoryService.getCategory(categoryId))
    }
}

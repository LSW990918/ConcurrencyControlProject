package com.a03.concurrencycontrolproject.domain.category.controller

import com.a03.concurrencycontrolproject.domain.category.service.CategoryService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CetegoryController(
    private val categoryService: CategoryService
) {
}
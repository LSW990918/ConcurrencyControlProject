package com.a03.concurrencycontrolproject.domain.review.dto

data class ReviewResponse(
    val id: Long,
    val comment: String,
    val score: Int,
)

package com.a03.concurrencycontrolproject.domain.review.dto

data class DeleteReviewRequest(
    val id: Long,
    val goodsId: Long
)

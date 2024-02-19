package com.a03.concurrencycontrolproject.domain.review.dto

import com.a03.concurrencycontrolproject.domain.review.model.Review

data class ReviewResponse(
    val id: Long,
    val comment: String,
    val score: Int,
) {

    companion object {
        fun from(review: Review) = ReviewResponse(
            id = review.id!!,
            comment = review.comment,
            score = review.score,
        )
    }

}

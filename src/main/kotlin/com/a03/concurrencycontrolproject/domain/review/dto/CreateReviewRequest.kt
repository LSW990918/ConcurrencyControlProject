package com.a03.concurrencycontrolproject.domain.review.dto

import com.a03.concurrencycontrolproject.domain.review.model.Review

data class CreateReviewRequest(
    val goodsId: Long,
    val score: Int,
    val comment: String,
) {
    fun to(goods: Goods, user: User) = Review(
        score = score,
        comment = comment,
        goods = goods,
        user = user,
    )
}

package com.a03.concurrencycontrolproject.domain.review.repository

import com.a03.concurrencycontrolproject.domain.review.model.Review
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ReviewQueryDslRepository {
    fun findReviewsByGoodsId(goodsId: Long, pageable: Pageable): Page<Review>

}
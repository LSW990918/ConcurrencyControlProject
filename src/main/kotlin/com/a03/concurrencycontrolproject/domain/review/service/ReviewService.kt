package com.a03.concurrencycontrolproject.domain.review.service

import com.a03.concurrencycontrolproject.domain.review.dto.CreateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.ReviewResponse
import com.a03.concurrencycontrolproject.domain.review.dto.UpdateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.repository.ReviewRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val goodsRepository: GoodsRepository,
    private val reviewRepository: ReviewRepository
) {
    fun createReview(createReviewRequest: CreateReviewRequest, user: User) {
        check(!goodsRepository.findByIdOrNull(createReviewRequest.goodsId)) { throw IllegalArgumentException() }

        val goods = goodsRepository.findByIdOrNull(createReviewRequest.goodsId)

        reviewRepository.save(createReviewRequest.to(goods, user))
    }


    fun updateReview(updateReviewRequest: UpdateReviewRequest, user: User) {
        check(!goodsRepository.findByIdOrNull(updateReviewRequest.goodsId)) { throw IllegalArgumentException() }

        val goods = goodsRepository.findByIdOrNull(updateReviewRequest.goodsId)
        val review = reviewRepository.findByIdOrNull(updateReviewRequest.id) ?: throw Exception()

        review.checkAuthorization(user)
        reviewRepository.save(updateReviewRequest.to(goods, user))
    }


    fun getReviewList(goodsId: Long): List<ReviewResponse> {
        check(!goodsRepository.findByIdOrNull(goodsId)) { throw IllegalArgumentException() }
        return reviewRepository.findReviewsByGoodsId(goodsId)
    }


    fun deleteReview(goodsId: Long, reviewId: Long, user: User) {
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw  IllegalArgumentException()

        review.checkAuthorization(user)

        reviewRepository.delete(review)
    }


}

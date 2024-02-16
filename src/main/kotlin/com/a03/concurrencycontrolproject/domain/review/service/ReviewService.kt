package com.a03.concurrencycontrolproject.domain.review.service

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import com.a03.concurrencycontrolproject.domain.review.dto.CreateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.ReviewResponse
import com.a03.concurrencycontrolproject.domain.review.dto.UpdateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.repository.ReviewRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val goodsRepository: GoodsRepository,
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository
) {
    fun createReview(createReviewRequest: CreateReviewRequest, userPrincipal: UserPrincipal) {
        goodsRepository.findByIdOrNull(createReviewRequest.goodsId) ?:throw IllegalArgumentException()

        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw Exception()
        val goods = goodsRepository.findByIdOrNull(createReviewRequest.goodsId) ?: throw Exception()

        reviewRepository.save(createReviewRequest.to(goods, user))
    }


    fun updateReview(updateReviewRequest: UpdateReviewRequest, userPrincipal: UserPrincipal) {
        goodsRepository.findByIdOrNull(updateReviewRequest.goodsId) ?: throw IllegalArgumentException()

        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw Exception()
        val goods = goodsRepository.findByIdOrNull(updateReviewRequest.goodsId) ?: throw Exception()
        val review = reviewRepository.findByIdOrNull(updateReviewRequest.id) ?: throw Exception()

        review.checkAuthorization(user)
        reviewRepository.save(updateReviewRequest.to(goods, user))
    }


    fun getReviewList(goodsId: Long): List<ReviewResponse> {
        goodsRepository.findByIdOrNull(goodsId) ?: throw IllegalArgumentException()
        return reviewRepository.findReviewsByGoodsId(goodsId)
    }


    fun deleteReview(goodsId: Long, reviewId: Long, userPrincipal: UserPrincipal) {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw Exception()
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw  IllegalArgumentException()

        review.checkAuthorization(user)

        reviewRepository.delete(review)
    }


}

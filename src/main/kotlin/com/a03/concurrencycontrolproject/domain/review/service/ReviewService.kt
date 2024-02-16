package com.a03.concurrencycontrolproject.domain.review.service

import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import com.a03.concurrencycontrolproject.domain.review.dto.CreateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.DeleteReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.ReviewResponse
import com.a03.concurrencycontrolproject.domain.review.dto.UpdateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.repository.ReviewRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.ui.Model

@Service
class ReviewService(
    private val goodsRepository: GoodsRepository,
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository
) {
    fun createReview(createReviewRequest: CreateReviewRequest, userPrincipal: UserPrincipal) {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw ModelNotFoundException("User", userPrincipal.id)
        val goods = goodsRepository.findByIdOrNull(createReviewRequest.goodsId) ?: throw ModelNotFoundException("Goods", createReviewRequest.goodsId)

        reviewRepository.save(createReviewRequest.to(goods, user))
    }


    fun updateReview(updateReviewRequest: UpdateReviewRequest, userPrincipal: UserPrincipal) {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw ModelNotFoundException("User", userPrincipal.id)
        val goods = goodsRepository.findByIdOrNull(updateReviewRequest.goodsId) ?: throw ModelNotFoundException("Goods", updateReviewRequest.goodsId)
        val review = reviewRepository.findByIdOrNull(updateReviewRequest.id) ?: throw ModelNotFoundException("Review", updateReviewRequest.id)

        review.checkAuthorization(user)
        reviewRepository.save(updateReviewRequest.to(goods, user))
    }


    fun getReviewList(goodsId: Long): List<ReviewResponse> {
        goodsRepository.findByIdOrNull(goodsId) ?: throw ModelNotFoundException("Goods", goodsId)
        return reviewRepository.findReviewsByGoodsId(goodsId)
    }


    fun deleteReview(deleteReviewRequest: DeleteReviewRequest, userPrincipal: UserPrincipal) {
        val user = userRepository.findByIdOrNull(userPrincipal.id) ?: throw ModelNotFoundException("User", userPrincipal.id)
        val review = reviewRepository.findByIdOrNull(deleteReviewRequest.id) ?: throw  ModelNotFoundException("Review", deleteReviewRequest.id)

        review.checkAuthorization(user)

        reviewRepository.delete(review)
    }


}

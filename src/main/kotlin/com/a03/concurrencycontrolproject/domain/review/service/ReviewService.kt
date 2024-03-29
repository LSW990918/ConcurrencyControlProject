package com.a03.concurrencycontrolproject.domain.review.service

import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import com.a03.concurrencycontrolproject.domain.review.dto.CreateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.ReviewResponse
import com.a03.concurrencycontrolproject.domain.review.dto.UpdateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.repository.ReviewRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReviewService(
    private val goodsRepository: GoodsRepository,
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository
) {
    fun createReview(createReviewRequest: CreateReviewRequest, userPrincipal: UserPrincipal) {
        val user =
            userRepository.findByIdOrNull(userPrincipal.id) ?: throw ModelNotFoundException("User", userPrincipal.id)
        val goods = goodsRepository.findByIdOrNull(createReviewRequest.goodsId) ?: throw ModelNotFoundException(
            "Goods",
            createReviewRequest.goodsId
        )

        reviewRepository.save(createReviewRequest.to(goods, user))
    }


    @Transactional
    fun updateReview(updateReviewRequest: UpdateReviewRequest, userPrincipal: UserPrincipal) {
        val user =
            userRepository.findByIdOrNull(userPrincipal.id) ?: throw ModelNotFoundException("User", userPrincipal.id)
        val goods = goodsRepository.findByIdOrNull(updateReviewRequest.goodsId) ?: throw ModelNotFoundException(
            "Goods",
            updateReviewRequest.goodsId
        )
        val review = reviewRepository.findByIdOrNull(updateReviewRequest.id) ?: throw ModelNotFoundException(
            "Review",
            updateReviewRequest.id
        )

        review.checkAuthorization(user)
        reviewRepository.save(updateReviewRequest.to(goods, user))
    }


    fun getReviewList(goodsId: Long, pageable: Pageable): Page<ReviewResponse> {
        goodsRepository.findByIdOrNull(goodsId) ?: throw ModelNotFoundException("Goods", goodsId)
        return reviewRepository.findReviewsByGoodsId(goodsId, pageable).map { ReviewResponse.from(it) }
    }


    fun deleteReview(reviewId: Long, userPrincipal: UserPrincipal) {
        val user =
            userRepository.findByIdOrNull(userPrincipal.id) ?: throw ModelNotFoundException("User", userPrincipal.id)
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)

        review.checkAuthorization(user)

        reviewRepository.delete(review)
    }

}

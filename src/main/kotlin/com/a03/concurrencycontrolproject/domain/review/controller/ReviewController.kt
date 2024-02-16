package com.a03.concurrencycontrolproject.domain.review.controller

import com.a03.concurrencycontrolproject.domain.review.dto.CreateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.ReviewResponse
import com.a03.concurrencycontrolproject.domain.review.dto.UpdateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.service.ReviewService
import com.sun.security.auth.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController("/goods/{goodsId}/reviews")
class ReviewController(
    private val reviewService: ReviewService
) {

    @PostMapping
    fun createReview(@PathVariable goodsId: Long, @RequestBody createReviewRequest: CreateReviewRequest, authentication: Authentication): ResponseEntity<Unit> {
        val userPrincipal = authentication.principal as UserPrincipal

        return reviewService.createReview(createReviewRequest, userPrincipal.to()).let { ResponseEntity.status(HttpStatus.CREATED) }.build()
    }

    @PatchMapping("/{reviewId}")
    fun updateReview(@PathVariable goodsId: Long, @PathVariable reviewId: Long, @RequestBody updateReviewRequest: UpdateReviewRequest, authentication: Authentication): ResponseEntity<Unit> {
        val userPrincipal = authentication.principal as UserPrincipal

        val requests = UpdateReviewRequest(
            id = reviewId,
            goodsId = goodsId,
            score = updateReviewRequest.score,
            comment = updateReviewRequest.comment
        )
        return reviewService.updateReview(requests, userPrincipal.to()).let { ResponseEntity.status(HttpStatus.OK) }.build()
    }

    @GetMapping
    fun getReviewList(@RequestParam goodsId: Long): ResponseEntity<List<ReviewResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewList(goodsId))
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(@PathVariable goodsId: Long, @PathVariable reviewId: Long, authentication: Authentication): ResponseEntity<Unit> {
        val userPrincipal = authentication.principal as UserPrincipal

        return reviewService.deleteReview(goodsId, reviewId, userPrincipal.to()).let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
    }
}
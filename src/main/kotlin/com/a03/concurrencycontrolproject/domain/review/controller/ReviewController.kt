package com.a03.concurrencycontrolproject.domain.review.controller

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.review.dto.CreateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.DeleteReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.ReviewResponse
import com.a03.concurrencycontrolproject.domain.review.dto.UpdateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.service.ReviewService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/goods/{goodsId}/reviews")
class ReviewController(
    private val reviewService: ReviewService
) {

    @PostMapping
    fun createReview(@RequestBody createReviewRequest: CreateReviewRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): ResponseEntity<Unit> {
        return reviewService.createReview(createReviewRequest, userPrincipal).let { ResponseEntity.status(HttpStatus.CREATED) }.build()
    }

    @PatchMapping
    fun updateReview(@RequestBody updateReviewRequest: UpdateReviewRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): ResponseEntity<Unit> {
        val requests = UpdateReviewRequest(
            id = updateReviewRequest.id,
            goodsId = updateReviewRequest.goodsId,
            score = updateReviewRequest.score,
            comment = updateReviewRequest.comment
        )
        return reviewService.updateReview(requests, userPrincipal).let { ResponseEntity.status(HttpStatus.OK) }.build()
    }

    @GetMapping
    fun getReviewList(@RequestParam goodsId: Long): ResponseEntity<List<ReviewResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewList(goodsId))
    }

    @DeleteMapping
    fun deleteReview(@RequestBody deleteReviewRequest: DeleteReviewRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): ResponseEntity<Unit> {
        return reviewService.deleteReview(deleteReviewRequest, userPrincipal).let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
    }
}
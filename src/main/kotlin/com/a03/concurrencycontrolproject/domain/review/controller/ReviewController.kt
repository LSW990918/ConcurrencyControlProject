package com.a03.concurrencycontrolproject.domain.review.controller

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.review.dto.CreateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.dto.ReviewResponse
import com.a03.concurrencycontrolproject.domain.review.dto.UpdateReviewRequest
import com.a03.concurrencycontrolproject.domain.review.service.ReviewService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/goods/{goodsId}/reviews")
class ReviewController(
    private val reviewService: ReviewService,
) {

    @Operation(summary = "리뷰 생성")
    @PostMapping
    fun createReview(@RequestBody createReviewRequest: CreateReviewRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): ResponseEntity<Unit> {
        return reviewService.createReview(createReviewRequest, userPrincipal).let { ResponseEntity.status(HttpStatus.CREATED) }.build()
    }

      
    @Operation(summary = "리뷰 수정")
    @PatchMapping()
    fun updateReview(@RequestBody updateReviewRequest: UpdateReviewRequest, @AuthenticationPrincipal userPrincipal: UserPrincipal): ResponseEntity<Unit> {

        val requests = UpdateReviewRequest(
            id = updateReviewRequest.id,
            goodsId = updateReviewRequest.goodsId,
            score = updateReviewRequest.score,
            comment = updateReviewRequest.comment
        )
        return reviewService.updateReview(requests, userPrincipal).let { ResponseEntity.status(HttpStatus.OK) }.build()
    }

    @Operation(summary = "리뷰 목록 조회")
    @GetMapping
    fun getReviewList(@RequestParam goodsId: Long, pageable: Pageable): ResponseEntity<Page<ReviewResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviewList(goodsId, pageable))
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    fun deleteReview(@PathVariable reviewId: Long, @AuthenticationPrincipal userPrincipal: UserPrincipal): ResponseEntity<Unit> {
        return reviewService.deleteReview(reviewId, userPrincipal).let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }
    }
}
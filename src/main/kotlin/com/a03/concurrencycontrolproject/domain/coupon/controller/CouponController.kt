package com.a03.concurrencycontrolproject.domain.coupon.controller

import com.a03.concurrencycontrolproject.domain.coupon.dto.CreateCouponRequest
import com.a03.concurrencycontrolproject.domain.coupon.service.CouponService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val couponService: CouponService
) {
    @Operation(summary = "굿즈에 사용할 쿠폰 생성")
    @PostMapping
    fun createCoupon(@RequestBody createCouponRequest: CreateCouponRequest): ResponseEntity<Unit> {
        val id = couponService.createCoupon(createCouponRequest)
        return ResponseEntity.ok().build()
    }
}
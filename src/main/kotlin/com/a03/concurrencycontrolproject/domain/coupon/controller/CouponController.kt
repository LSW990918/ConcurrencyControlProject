package com.a03.concurrencycontrolproject.domain.coupon.controller

import com.a03.concurrencycontrolproject.domain.coupon.dto.CreateCouponRequest
import com.a03.concurrencycontrolproject.domain.coupon.service.CouponService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/coupons")
class CouponController(
    private val couponService: CouponService
) {

    @PostMapping
    fun createCoupon(@RequestBody createCouponRequest: CreateCouponRequest): ResponseEntity<Unit> {
        val id = couponService.createCoupon(createCouponRequest)
        return ResponseEntity.created(URI.create(String.format("/coupons/%d"), id)).build()
    }
}
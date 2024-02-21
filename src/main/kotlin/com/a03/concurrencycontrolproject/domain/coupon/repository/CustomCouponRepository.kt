package com.a03.concurrencycontrolproject.domain.coupon.repository

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon

interface CustomCouponRepository {
    fun insertCoupon(coupons: List<Coupon>)
}
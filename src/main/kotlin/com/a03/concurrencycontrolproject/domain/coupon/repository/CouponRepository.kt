package com.a03.concurrencycontrolproject.domain.coupon.repository

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import org.springframework.data.jpa.repository.JpaRepository


interface CouponRepository: JpaRepository<Coupon, Long>, CustomCouponRepository {
}
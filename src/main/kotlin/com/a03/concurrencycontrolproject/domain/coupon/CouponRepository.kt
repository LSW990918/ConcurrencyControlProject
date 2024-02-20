package com.a03.concurrencycontrolproject.domain.coupon

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import org.springframework.data.jpa.repository.JpaRepository


interface CouponRepository: JpaRepository<Coupon, Long> {
}
package com.a03.concurrencycontrolproject.domain.coupon.repository

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CustomCouponRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
): CustomCouponRepository {
    override fun insertCoupon(coupons: List<Coupon>) {
//        val sql =
    }
}
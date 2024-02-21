package com.a03.concurrencycontrolproject.domain.coupon.repository

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import org.springframework.jdbc.core.BatchPreparedStatementSetter
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.Timestamp

@Repository
class CustomCouponRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
): CustomCouponRepository {
    override fun insertCoupon(coupons: List<Coupon>) {
        val sql = "INSERT INTO coupon(coupon_amount, coupon_available_date, coupon_expire_date, coupon_name, coupon_number, goods_id) VALUES (?, ?, ?, ?, ?, ?)"

        jdbcTemplate.batchUpdate(sql, object : BatchPreparedStatementSetter {
            override fun setValues(ps: PreparedStatement, i: Int) {
                ps.setLong(1, coupons[i].couponAmount)
                ps.setTimestamp(2, Timestamp.valueOf(coupons[i].couponAvailableDate))
                ps.setTimestamp(3, Timestamp.valueOf(coupons[i].couponExpireDate))
                ps.setString(4, coupons[i].couponName)
                ps.setString(5, coupons[i].couponNumber)
                ps.setLong(6, coupons[i].goods.id!!)
            }

            override fun getBatchSize(): Int {
                return coupons.size
            }
        })
    }
}
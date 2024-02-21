package com.a03.concurrencycontrolproject.domain.coupon.dto

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


data class CreateCouponRequest (
    val goodsId: Long,
    val couponName: String,
    val couponAmount: Long,
    val couponAvailableDate: LocalDateTime,
    val couponExpireDate: LocalDateTime,
) {
    fun to(goods: Goods) = Coupon(
        goods = goods,
        couponName = couponName,
        couponNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 31),
        couponAmount = couponAmount,
        couponAvailableDate = couponAvailableDate,
        couponExpireDate = couponExpireDate
    )
}

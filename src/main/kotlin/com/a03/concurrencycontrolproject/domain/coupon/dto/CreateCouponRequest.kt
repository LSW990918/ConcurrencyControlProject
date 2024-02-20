package com.a03.concurrencycontrolproject.domain.coupon.dto

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import java.time.LocalDateTime


data class CreateCouponRequest (
    val goodsId: Long,
    val couponName: String,
    val couponNumber: String,
    val couponAmount: Long,
    val couponAvailableDate: LocalDateTime,
    val couponExpireDate: LocalDateTime,
) {
    fun to(goods: Goods) = Coupon(
        goods = goods,
        couponName = couponName,
        couponNumber = couponNumber,
        couponAmount = couponAmount,
        couponAvailableDate = couponAvailableDate,
        couponExpireDate = couponExpireDate
    )
}

package com.a03.concurrencycontrolproject.domain.coupon.dto

import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


data class CreateCouponRequest (
    val goodsId: Long,
    val couponName: String,
    val couponAmount: Long,

    @field: Pattern(
        regexp = CreateCouponRequest.dateRegexp,
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    @Schema(pattern = CreateCouponRequest.dateRegexp)
    @JsonProperty("couponAvailableDate")
    val _couponAvailableDate: String,

    @field: Pattern(
        regexp = CreateCouponRequest.dateRegexp,
        message = "Please match date format yyyy-MM-dd HH:mm:ss"
    )
    @JsonProperty("couponExpireDate")
    @Schema(pattern = CreateCouponRequest.dateRegexp)
    val _couponExpireDate: String,
) {

    @JsonIgnore
    val couponAvailableDate: LocalDateTime = convertStringToLocalDateTime(this._couponAvailableDate)

    @JsonIgnore
    val couponExpireDate: LocalDateTime = convertStringToLocalDateTime(this._couponExpireDate)

    private fun convertStringToLocalDateTime(date: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return LocalDateTime.parse(date, formatter)
    }

    fun to(goods: Goods) = Coupon(
        goods = goods,
        couponName = couponName,
        couponNumber = UUID.randomUUID().toString().replace("-", "").substring(0, 31),
        couponAmount = couponAmount,
        couponAvailableDate = couponAvailableDate,
        couponExpireDate = couponExpireDate
    )

    companion object {
        private const val dateRegexp =
            "^2[0-9]{3}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01]) (0[1-9]|1[0-9]|2[0-3]):(0[1-9]|[1-5][0-9]):(0[1-9]|[1-5][0-9])\$"
    }
}

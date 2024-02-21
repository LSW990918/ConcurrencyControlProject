package com.a03.concurrencycontrolproject.domain.coupon.service

import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.domain.coupon.dto.CreateCouponRequest
import com.a03.concurrencycontrolproject.domain.coupon.model.Coupon
import com.a03.concurrencycontrolproject.domain.coupon.repository.CouponRepository
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CouponService(
    private val goodsRepository: GoodsRepository,
    private val couponRepository: CouponRepository
) {
    fun createCoupon(createCouponRequest: CreateCouponRequest) {
        val goods = goodsRepository.findByIdOrNull(createCouponRequest.goodsId) ?: throw ModelNotFoundException("Goods", createCouponRequest.goodsId)
        val couponList = mutableListOf<Coupon>()

        check(createCouponRequest.couponExpireDate.isBefore(createCouponRequest.couponAvailableDate)) {
            throw IllegalStateException("쿠폰의 만료기간과 이용 가능 기간을 다시 입력해주세요")
        }

        for (i in 0..createCouponRequest.couponAmount) {
            couponList.add(createCouponRequest.to(goods))
        }
        couponRepository.saveAll(couponList)

    }

}
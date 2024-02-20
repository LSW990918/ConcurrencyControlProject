package com.a03.concurrencycontrolproject.domain.coupon.service

import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.domain.coupon.CouponRepository
import com.a03.concurrencycontrolproject.domain.coupon.dto.CreateCouponRequest
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

    }

}
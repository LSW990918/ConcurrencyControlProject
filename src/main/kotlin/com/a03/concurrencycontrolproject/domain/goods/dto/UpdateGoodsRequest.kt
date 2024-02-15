package com.a03.concurrencycontrolproject.domain.goods.dto

data class UpdateGoodsRequest(
    val title: String,
    val runningTime: Int,
    val price: Int,
    val categoryId: Long
) {
    var goodsId: Long = 0L
    var userId: Long = 0L
}
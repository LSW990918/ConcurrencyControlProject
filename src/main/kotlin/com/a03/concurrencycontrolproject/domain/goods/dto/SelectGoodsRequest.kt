package com.a03.concurrencycontrolproject.domain.goods.dto

data class SelectGoodsRequest(
    val categoryId: Long,
    val title: String?,
    val place: String?
)

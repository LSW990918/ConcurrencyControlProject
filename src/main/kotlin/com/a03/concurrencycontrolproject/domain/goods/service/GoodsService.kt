package com.a03.concurrencycontrolproject.domain.goods.service

import com.a03.concurrencycontrolproject.domain.goods.dto.CreateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.goods.dto.UpdateGoodsRequest

interface GoodsService {

    fun createGoods(request: CreateGoodsRequest)

    fun updateGoods(request: UpdateGoodsRequest)

    fun deleteGoods(goodsId: Long)

    fun getGoodsList(categoryId: Long): List<GoodsResponse>

    fun getGoods(goodsId: Long): GoodsResponse

}
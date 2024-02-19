package com.a03.concurrencycontrolproject.domain.goods.service

import com.a03.concurrencycontrolproject.domain.goods.dto.CreateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.goods.dto.SelectGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.UpdateGoodsRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GoodsService {

    fun createGoods(request: CreateGoodsRequest)

    fun updateGoods(request: UpdateGoodsRequest)

    fun deleteGoods(userId: Long, goodsId: Long)

    fun getGoodsList(pageable: Pageable, selectGoodsRequest: SelectGoodsRequest): Page<GoodsResponse>

    fun getGoods(goodsId: Long): GoodsResponse

}
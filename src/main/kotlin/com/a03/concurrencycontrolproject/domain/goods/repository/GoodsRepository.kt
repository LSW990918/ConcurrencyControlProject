package com.a03.concurrencycontrolproject.domain.goods.repository

import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import org.springframework.data.jpa.repository.JpaRepository

interface GoodsRepository : JpaRepository<Goods, Long>, CustomGoodsRepository {
    fun findByCategoryId(categoryId: Long): List<Goods>

}
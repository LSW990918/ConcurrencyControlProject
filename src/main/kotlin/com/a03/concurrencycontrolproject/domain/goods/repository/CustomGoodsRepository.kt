package com.a03.concurrencycontrolproject.domain.goods.repository

import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomGoodsRepository {

    fun findByPageableAndCategoryIdAndTitleAndPlace(
        pageable: Pageable,
        categoryId: Long,
        title: String?,
        place: String?
    ): Page<Goods>

}
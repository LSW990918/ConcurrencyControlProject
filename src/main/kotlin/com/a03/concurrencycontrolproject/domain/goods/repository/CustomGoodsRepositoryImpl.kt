package com.a03.concurrencycontrolproject.domain.goods.repository

import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.goods.model.QGoods
import com.a03.concurrencycontrolproject.infra.querydsl.QueryDslSupport
import com.querydsl.core.BooleanBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CustomGoodsRepositoryImpl : QueryDslSupport(), CustomGoodsRepository {
    private val goods = QGoods.goods
    override fun findByPageableAndCategoryIdAndTitleAndPlace(
        pageable: Pageable,
        categoryId: Long,
        title: String?,
        place: String?
    ): Page<Goods> {

        val whereClause = BooleanBuilder()
        whereClause.and(goods.category.id.eq(categoryId))
        title?.let { whereClause.and(goods.title.contains(title)) }
        place?.let { whereClause.and(goods.place.contains(place)) }

        val totalCount = queryFactory.select(goods.count()).from(goods).where(whereClause).fetchOne() ?: 0L

        val query = queryFactory.selectFrom(goods)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        if (pageable.sort.isSorted) {
            when (pageable.sort.first()?.property) {
                "id" -> query.orderBy(goods.id.desc())
                "title" -> query.orderBy(goods.title.desc())
                else -> query.orderBy(goods.id.desc())
            }
        } else {
            query.orderBy(goods.id.desc())
        }

        val contents = query.fetch()

        return PageImpl(contents, pageable, totalCount)

    }

}
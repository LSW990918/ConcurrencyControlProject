package com.a03.concurrencycontrolproject.domain.category.repository

import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.category.model.QCategory
import com.a03.concurrencycontrolproject.domain.goods.model.QGoods
import com.a03.concurrencycontrolproject.infra.querydsl.QueryDslSupport
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class CategoryRepositoryImpl : QueryDslSupport(), CustomCategoryRepository {

    private val category = QCategory.category
    override fun findByPageable(pageable: Pageable): Page<Category> {

        val totalCount = queryFactory.select(category.count()).from(category).fetchOne() ?: 0L

        val goods = QGoods.goods
        val contents = queryFactory.select(category)
            .leftJoin(category.goodsList, goods)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*getOrderSpecifier(pageable, category))
            .fetch()

        return PageImpl(contents, pageable, totalCount)
    }

    private fun getOrderSpecifier(pageable: Pageable, path: EntityPathBase<*>): Array<OrderSpecifier<*>> {
        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageable.sort.toList().map { order ->
            OrderSpecifier(
                if (order.isAscending) Order.ASC else Order.DESC,
                pathBuilder.get(order.property) as Expression<Comparable<*>>
            )
        }.toTypedArray()
    }
}
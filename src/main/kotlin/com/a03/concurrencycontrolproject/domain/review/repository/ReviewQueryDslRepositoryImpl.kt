package com.a03.concurrencycontrolproject.domain.review.repository

import com.a03.concurrencycontrolproject.domain.goods.model.QGoods
import com.a03.concurrencycontrolproject.domain.review.model.QReview
import com.a03.concurrencycontrolproject.domain.review.model.Review
import com.a03.concurrencycontrolproject.infra.querydsl.QueryDslSupport
import com.querydsl.core.BooleanBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class ReviewQueryDslRepositoryImpl: QueryDslSupport(), ReviewQueryDslRepository {
    private val goods = QGoods.goods
    private val review = QReview.review
    override fun findReviewsByGoodsId(goodsId: Long, pageable: Pageable): Page<Review> {
        val builder = BooleanBuilder()

        goodsId.let { builder.and(review.goods.id.eq(goodsId)) }

        val totalCount = queryFactory.select(review.count()).from(review).where(builder).fetchOne() ?: 0L

        val contents = queryFactory.selectFrom(review)
            .where(builder)
            .leftJoin(review.goods, goods)
            .fetchJoin()
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(review.id.asc())
            .fetch()

        return PageImpl(contents, pageable, totalCount)
    }
}
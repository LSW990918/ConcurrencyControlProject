package com.a03.concurrencycontrolproject.domain.goods.service

import com.a03.concurrencycontrolproject.common.exception.AccessDeniedException
import com.a03.concurrencycontrolproject.common.exception.IllegalDateStateException
import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.domain.category.repository.CategoryRepository
import com.a03.concurrencycontrolproject.domain.goods.dto.CreateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.goods.dto.SelectGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.UpdateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRole
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GoodsServiceImpl(
    private val goodsRepository: GoodsRepository,
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
) : GoodsService {

    @Transactional
    override fun createGoods(request: CreateGoodsRequest) {

        val category = categoryRepository.findByIdOrNull(request.categoryId)
            ?: throw ModelNotFoundException("Category", request.categoryId)

        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", request.userId)

        if (request.date < request.bookableDate) {
            throw IllegalDateStateException(
                "BookableDate",
                "Date",
                request.bookableDate.toString(),
                request.date.toString()
            )
        }

        goodsRepository.save(request.to(category, user))
    }

    @Transactional
    override fun updateGoods(request: UpdateGoodsRequest) {
        val goods = goodsRepository.findByIdOrNull(request.goodsId)
            ?: throw ModelNotFoundException("Goods", request.goodsId)

        val user = userRepository.findByIdOrNull(request.userId)
            ?: throw ModelNotFoundException("User", request.userId)

        val category = categoryRepository.findByIdOrNull(request.categoryId)
            ?: throw ModelNotFoundException("User", request.userId)


        if (goods.user.role != UserRole.ADMIN && goods.user.id != user.id) {
            throw AccessDeniedException(user.id!!)
        }

        request.let {
            goods.category = category
            goods.runningTime = it.runningTime
            goods.price = it.price
            goods.title = it.title
        }
    }

    override fun deleteGoods(userId: Long, goodsId: Long) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)

        val goods = goodsRepository.findByIdOrNull(goodsId)
            ?: throw ModelNotFoundException("Goods", goodsId)

        if (goods.user.role != UserRole.ADMIN && goods.user.id != user.id) {
            throw AccessDeniedException(user.id!!)
        }
        goodsRepository.delete(goods)
    }

    override fun getGoodsList(pageable: Pageable, selectGoodsRequest: SelectGoodsRequest): Page<GoodsResponse> {
        categoryRepository.findByIdOrNull(selectGoodsRequest.categoryId)
            ?: throw ModelNotFoundException("Category", selectGoodsRequest.categoryId)

        return goodsRepository.findByPageableAndCategoryIdAndTitleAndPlace(
            pageable = pageable,
            categoryId = selectGoodsRequest.categoryId,
            title = selectGoodsRequest.title,
            place = selectGoodsRequest.place
        ).map { GoodsResponse.from(it) }
    }

    override fun getGoods(goodsId: Long): GoodsResponse {
        return goodsRepository.findByIdOrNull(goodsId)?.let { GoodsResponse.from(it) }
            ?: throw ModelNotFoundException("Goods", goodsId)
    }

}
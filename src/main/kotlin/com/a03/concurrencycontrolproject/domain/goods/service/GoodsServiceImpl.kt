package com.a03.concurrencycontrolproject.domain.goods.service

import com.a03.concurrencycontrolproject.domain.category.model.Category
import com.a03.concurrencycontrolproject.domain.category.repository.CategoryRepository
import com.a03.concurrencycontrolproject.domain.goods.dto.CreateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.goods.dto.UpdateGoodsRequest
import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GoodsServiceImpl(
    val goodsRepository: GoodsRepository,
    val categoryRepository: CategoryRepository,
//    val userRepository
) : GoodsService {

    @Transactional
    override fun createGoods(request: CreateGoodsRequest) {
        // TODO RuntimeException 변경
        val category = categoryRepository.findByIdOrNull(request.categoryId) ?: throw RuntimeException("")
        // TODO get user or throw
        // TODO toEntity(category,user)
        goodsRepository.save(request.toEntity(category))
    }

    @Transactional
    override fun updateGoods(request: UpdateGoodsRequest) {
        // TODO goods 생성자와 userId validation


        TODO("Not yet implemented")
    }

    override fun deleteGoods(goodsId: Long) {
        TODO("Not yet implemented")
    }

    override fun getGoodsList(categoryId: Long): List<GoodsResponse> {
        // TODO RuntimeException 변경
        categoryRepository.findByIdOrNull(categoryId) ?: throw RuntimeException("")

        return goodsRepository.findByCategoryId(categoryId).map { it.toResponse() }
    }

    override fun getGoods(goodsId: Long): GoodsResponse {
        TODO("Not yet implemented")
    }

}

// TODO user:User 추가
private fun CreateGoodsRequest.toEntity(category: Category): Goods {
    return Goods(
        title = title,
        runningTime = runningTime,
        date = convertDate,
        bookableDate = convertBookableDate,
        ticketAmount = ticketAmount,
        price = price,
        place = place,
        category = category,
//        user = user,
    )
}

private fun Goods.toResponse(): GoodsResponse {
    return GoodsResponse(
        id = id!!,
        title = title,
        place = place,
        runningTime = runningTime,
        bookableDate = bookableDate,
        date = date,
        ticketAmount = ticketAmount,
        // TODO ticket
        availableTicketAmount = 1,
        price = price
    )
}

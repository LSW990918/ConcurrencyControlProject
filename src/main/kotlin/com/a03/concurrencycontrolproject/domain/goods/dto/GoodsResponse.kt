package com.a03.concurrencycontrolproject.domain.goods.dto

import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import java.io.Serializable
import java.time.LocalDateTime

data class GoodsResponse(
    val id: Long,
    val title: String,
    val place: String,
    val runningTime: Int,
    val bookableDate: LocalDateTime,
    val date: LocalDateTime,
    val ticketAmount: Int,
    val availableTicketAmount: Int,
    val price: Int
) : Serializable {
    companion object {
        fun from(goods: Goods): GoodsResponse {
            return GoodsResponse(
                id = goods.id!!,
                title = goods.title,
                place = goods.place,
                runningTime = goods.runningTime,
                bookableDate = goods.bookableDate,
                date = goods.date,
                ticketAmount = goods.ticketAmount,
                availableTicketAmount = goods.ticketAmount - goods.ticket.size,
                price = goods.price
            )
        }
    }
}
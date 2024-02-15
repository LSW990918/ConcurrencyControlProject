package com.a03.concurrencycontrolproject.domain.goods.dto

import java.time.LocalDateTime

data class GoodsResponse(
    val id: Long,
    val title: String,
    val place: String,
    val runningTime: Int,
    val bookableDate: LocalDateTime,
    val date: LocalDateTime,
    val ticketAmount: Int,
    val availableTicketAmount:Int,
    val price: Int
)

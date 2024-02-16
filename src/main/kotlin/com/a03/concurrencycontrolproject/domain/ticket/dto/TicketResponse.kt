package com.a03.concurrencycontrolproject.domain.ticket.dto

import com.a03.concurrencycontrolproject.domain.goods.model.Goods
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import com.a03.concurrencycontrolproject.domain.user.model.User

class TicketResponse(
    val id: Long,
    val goods: Goods,
    val user: User,
) {
    fun to(ticket: Ticket): TicketResponse {
        return TicketResponse(
            id = ticket.id!!,
            goods = ticket.goods,
            user = ticket.user
        )
    }

}
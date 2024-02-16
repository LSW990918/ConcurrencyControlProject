package com.a03.concurrencycontrolproject.domain.ticket.dto

import com.a03.concurrencycontrolproject.domain.goods.dto.GoodsResponse
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import com.a03.concurrencycontrolproject.domain.user.dto.UserResponse

class TicketResponse(
    val id: Long,
    val goods: GoodsResponse,
    val user: UserResponse,
) {
    companion object {
        fun from(ticket: Ticket): TicketResponse {
            return TicketResponse(
                id = ticket.id!!,
                goods = GoodsResponse.from(ticket.goods),
                user = UserResponse.from(ticket.user)
            )
        }
    }
}
package com.a03.concurrencycontrolproject.domain.ticket.dto

import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket

class TicketResponse(
    val id: Long,
    val goodsId: Long,
    val userId: Long,
) {
    companion object {
        fun Ticket.toResponse(): TicketResponse {
            return TicketResponse(
                id = id!!,
                goodsId = goods.id!!,
                userId = user.id!!
            )
        }
    }
}
package com.a03.concurrencycontrolproject.domain.ticket.service

import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse

interface TicketService {

    fun createTicket(request: CreateTicketRequest)

    fun deleteTicket(ticketId: Long)

    fun getTicketOfMember(): List<TicketResponse>

    fun getTicketOfGoods(goodsId: Long): List<TicketResponse>
}
package com.a03.concurrencycontrolproject.domain.ticket.service

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse

interface TicketService {

    fun createTicket(userId: Long, request: CreateTicketRequest)

    fun deleteTicket(userId: Long, ticketId: Long)

    fun getTicketOfMember(userId: Long): List<TicketResponse>

    fun getTicketOfGoods(goodsId: Long, userId: Long): List<TicketResponse>
}
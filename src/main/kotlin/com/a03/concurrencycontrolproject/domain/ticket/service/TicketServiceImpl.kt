package com.a03.concurrencycontrolproject.domain.ticket.service

import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse
import com.a03.concurrencycontrolproject.domain.ticket.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository
): TicketService {
    override fun createTicket(request: CreateTicketRequest) {
        TODO("Not yet implemented")
    }

    override fun deleteTicket(ticketId: Long) {
        TODO("Not yet implemented")
    }

    override fun getTicketOfMember(): List<TicketResponse> {
        TODO()
    }

    override fun getTicketOfGoods(goodsId: Long): List<TicketResponse> {
        TODO("Not yet implemented")
    }
}
package com.a03.concurrencycontrolproject.domain.ticket.service

import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse.Companion.toResponse
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import com.a03.concurrencycontrolproject.domain.ticket.repository.TicketRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository,
//    private val goodsRepository: GoodsRepository
): TicketService {
    override fun createTicket(request: CreateTicketRequest) {
//        val goods = goodsRepository.findByIdOrNull(request.goodsId)
//            ?: throw Exception()
//        val ticket = Ticket (
//            goods = goods,
//            user =
//        )
//        return ticket.toResponse()
        TODO()
    }

    override fun deleteTicket(ticketId: Long) {
        val ticket = ticketRepository.findByIdOrNull(ticketId)
            ?: throw Exception()
        ticketRepository.delete(ticket)
    }

    //아래 예외처리 수정 필요함
    override fun getTicketOfMember(): List<TicketResponse> {
        val ticketList = ticketRepository.findAllByUserId(1)
        return ticketList.map { it?.toResponse() ?: throw Exception() }
    }

    override fun getTicketOfGoods(goodsId: Long): List<TicketResponse> {
        val ticketList = ticketRepository.findAllByGoodsId(goodsId)
        return ticketList.map { it?.toResponse() ?: throw Exception() }
    }
}
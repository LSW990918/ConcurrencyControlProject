package com.a03.concurrencycontrolproject.domain.ticket.service

import com.a03.concurrencycontrolproject.common.exception.AccessDeniedException
import com.a03.concurrencycontrolproject.common.exception.ModelNotFoundException
import com.a03.concurrencycontrolproject.domain.goods.repository.GoodsRepository
import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import com.a03.concurrencycontrolproject.domain.ticket.model.accessUser
import com.a03.concurrencycontrolproject.domain.ticket.repository.TicketRepository
import com.a03.concurrencycontrolproject.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TicketServiceImpl(
    private val ticketRepository: TicketRepository,
    private val goodsRepository: GoodsRepository,
    private val userRepository: UserRepository
): TicketService {

    override fun createTicket(userId: Long, request: CreateTicketRequest) {
        val goods = goodsRepository.findByIdOrNull(request.goodsId)
            ?: throw ModelNotFoundException("Goods", request.goodsId)
        val user = userRepository.findByIdOrNull(userId)
            ?: throw ModelNotFoundException("User", userId)
        val ticket = Ticket (
            goods = goods,
            user = user
        )

        if(goods.ticketAmount - goods.ticket.size <= 0){
            throw NotEnoughTicketException(goods.id!!)
        }

        ticketRepository.save(ticket)
    }

    override fun deleteTicket(userId: Long, ticketId: Long) {
        val ticket = ticketRepository.findByIdOrNull(ticketId)
            ?: throw ModelNotFoundException("Ticket", ticketId)
        if (ticket.user.id!! != userId) throw AccessDeniedException(ticketId)
        ticketRepository.delete(ticket)
    }

    override fun getTicketOfMember(userId: Long): List<TicketResponse> {
        val ticketList = ticketRepository.findAllByUserId(userId)
            ?: throw ModelNotFoundException("TicketList", userId)
        val ticketUser = ticketRepository.findByUserId(userId)
            ?: throw ModelNotFoundException("Ticket",userId)
        accessUser(userId, ticketUser.user.id!!)
        return ticketList.map { TicketResponse.from(it) }
    }

    override fun getTicketOfGoods(goodsId: Long, userId: Long): List<TicketResponse> {
        val ticketList = ticketRepository.findAllByGoodsId(goodsId)
            ?: throw ModelNotFoundException("TicketList", goodsId)
        val goods = goodsRepository.findByIdOrNull(goodsId)
            ?: throw ModelNotFoundException("Goods", goodsId)
        accessUser(goods.user.id!!, userId)
        return ticketList.map { TicketResponse.from(it) }
    }
}
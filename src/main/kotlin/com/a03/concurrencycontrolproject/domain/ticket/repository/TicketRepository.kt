package com.a03.concurrencycontrolproject.domain.ticket.repository

import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository: JpaRepository<Ticket, Long>, CustomTicketRepository {
    fun findAllByUserId(userId: Long): List<Ticket>?

    fun findAllByGoodsId(goodsId: Long): List<Ticket>?
}
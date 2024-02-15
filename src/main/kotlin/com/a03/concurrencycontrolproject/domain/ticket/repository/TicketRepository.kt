package com.a03.concurrencycontrolproject.domain.ticket.repository

import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository

interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findAllByTicketId(ticketId: Long): List<Ticket>
}
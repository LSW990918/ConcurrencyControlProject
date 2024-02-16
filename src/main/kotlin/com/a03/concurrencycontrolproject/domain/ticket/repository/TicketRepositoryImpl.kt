package com.a03.concurrencycontrolproject.domain.ticket.repository

import com.a03.concurrencycontrolproject.domain.ticket.model.QTicket
import com.a03.concurrencycontrolproject.domain.ticket.model.Ticket
import com.a03.concurrencycontrolproject.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class TicketRepositoryImpl : QueryDslSupport(), CustomTicketRepository {
    private val ticket = QTicket.ticket
}
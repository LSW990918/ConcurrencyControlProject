package com.a03.concurrencycontrolproject.domain.ticket.controller

import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.service.TicketService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tickets")
class TicketController(
//    private val ticketService: TicketService
) {

    @PostMapping
    fun createTicket(
        @RequestBody request: CreateTicketRequest
    ): ResponseEntity<Unit> {
        TODO()
    }
}
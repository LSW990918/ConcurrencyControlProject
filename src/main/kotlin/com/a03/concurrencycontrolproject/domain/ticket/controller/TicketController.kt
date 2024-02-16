package com.a03.concurrencycontrolproject.domain.ticket.controller

import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse
import com.a03.concurrencycontrolproject.domain.ticket.service.TicketService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tickets")
class TicketController(
    private val ticketService: TicketService
) {

    @PostMapping
    fun createTicket(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: CreateTicketRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ticketService.createTicket(userPrincipal.id, request))
    }

    @DeleteMapping("/{ticketId}")
    fun deleteTicket(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable ticketId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(ticketService.deleteTicket(userPrincipal.id, ticketId))
    }

    @GetMapping("/mine")
    fun getTicketOfMember(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<List<TicketResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ticketService.getTicketOfMember(userPrincipal.id))
    }

    @GetMapping()
    fun getTicketOfGoods(
        @RequestParam goodsId: Long
    ): ResponseEntity<List<TicketResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ticketService.getTicketOfGoods(goodsId))
    }
}
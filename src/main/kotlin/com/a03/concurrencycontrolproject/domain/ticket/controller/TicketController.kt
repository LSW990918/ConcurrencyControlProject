package com.a03.concurrencycontrolproject.domain.ticket.controller

import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

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

    @DeleteMapping("/{ticketId}")
    fun deleteTicket(
        @PathVariable ticketInt: Long
    ): ResponseEntity<Unit> {
        TODO()
    }

    @GetMapping()//Path로 유저아이디를 받아야함 -> 토큰에서 받아올 방법 생각해보기
    fun getTicketOfMember(
//        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<List<TicketResponse>> {
        TODO()
    }

    @GetMapping("/{goodsId}")
    fun getTicketOfGoods(
        @PathVariable goodsId: Long
    ): ResponseEntity<List<TicketResponse>> {
        TODO()
    }
}
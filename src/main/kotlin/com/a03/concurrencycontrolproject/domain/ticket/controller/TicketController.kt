package com.a03.concurrencycontrolproject.domain.ticket.controller

import com.a03.concurrencycontrolproject.common.redis.RedisService
import com.a03.concurrencycontrolproject.common.security.jwt.UserPrincipal
import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.dto.TicketResponse
import com.a03.concurrencycontrolproject.domain.ticket.service.TicketService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tickets")
class TicketController(
    private val ticketService: TicketService,
    private val redisService: RedisService

) {

    @Operation(summary = "티켓 생성")
    @PostMapping
    fun createTicket(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody request: CreateTicketRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(redisService.createTicket(userPrincipal.id, request))
    }

    @Operation(summary = "티켓 취소")
    @DeleteMapping("/{ticketId}")
    fun deleteTicket(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable ticketId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(ticketService.deleteTicket(userPrincipal.id, ticketId))
    }

    @Operation(summary = "유저의 티켓 목록 조회")
    @GetMapping("/mine")
    fun getTicketOfMember(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<List<TicketResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ticketService.getTicketOfMember(userPrincipal.id))
    }

    @Operation(summary = "굿즈의 예매된 티켓 목록 조회")
    @GetMapping
    @PreAuthorize("hasAnyRole('SELLER')")
    fun getTicketOfGoods(
        @RequestParam goodsId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<List<TicketResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(ticketService.getTicketOfGoods(goodsId, userPrincipal.id))
    }
}
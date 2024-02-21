package com.a03.concurrencycontrolproject.common.redis

import RedisLockRepository
import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.service.TicketService
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val redisLockRepository: RedisLockRepository,
    private val ticketService: TicketService
){
    @Throws(InterruptedException::class)
    fun createTicket(userId: Long, request: CreateTicketRequest) {
        try {
            // Lock 획득 시도
            while (!redisLockRepository.lock(userId!!)) {
                //SpinLock 방식이 redis 에게 주는 부하를 줄여주기위한 sleep
                Thread.sleep(100)
            }
            //lock 획득 성공 시
            ticketService.createTicket(userId, request)
        } finally {
            //락 해제
            redisLockRepository.unlock(userId)
        }
    }
}
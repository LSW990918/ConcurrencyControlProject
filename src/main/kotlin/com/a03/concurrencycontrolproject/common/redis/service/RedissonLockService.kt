package com.a03.concurrencycontrolproject.common.redis.service

import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
import com.a03.concurrencycontrolproject.domain.ticket.service.TicketService
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit


@Component
class RedissonLockService(
    private val redissonClient: RedissonClient,
    private val ticketService: TicketService
) {
    fun createTicket(userid: Long, request: CreateTicketRequest) {
        //key 로 Lock 객체 가져옴
        val lock = redissonClient.getLock(request.goodsId.toString())

        try {
            //획득시도 시간, 락 점유 시간
            val available =lock.tryLock(5, 1, TimeUnit.SECONDS)

            if (!available) {
                println("lock 획득 실패")
                return
            }
            ticketService.createTicket(userid, request)
//        } catch (e: InterruptedException) {
//            throw RuntimeException(e)
        } finally {
            lock.unlock()
        }
    }
}
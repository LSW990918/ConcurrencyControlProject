//package com.a03.concurrencycontrolproject.common.redis.service
//
//import com.a03.concurrencycontrolproject.domain.ticket.dto.CreateTicketRequest
//import com.a03.concurrencycontrolproject.domain.ticket.service.TicketService
//import org.redisson.api.RedissonClient
//import org.springframework.stereotype.Component
//import java.util.concurrent.TimeUnit
//
//
//@Component
//class RedissonSpinLockService(
//    private val redissonClient: RedissonClient,
//    private val ticketService: TicketService
//) {
//    fun createTicket(userid: Long, request: CreateTicketRequest) {
//        //getSpinLock 으로 구현해봄
//        //근데 이게 SpinLock 방식이 맞는지 의문
//        val lock = redissonClient.getSpinLock(userid.toString())
//        try{
//            lock.tryLock(100, 100, TimeUnit.SECONDS)
//            ticketService.createTicket(userid,request)
//        } finally {
//            lock.unlock()
//        }
//
//    }
//}
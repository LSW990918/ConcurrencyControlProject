package com.a03.concurrencycontrolproject.common.aop.redisson.eventlock

import com.a03.concurrencycontrolproject.common.aop.ConcurrencyControlDto
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Aspect
@Component
class EventLockAspect(
    private val redissonClient: RedissonClient,
) {


    @Around("@annotation(com.a03.concurrencycontrolproject.common.aop.redisson.eventlock.EventLock) && args(..,request)")
    fun run(joinPoint: ProceedingJoinPoint, request: ConcurrencyControlDto) {
        val methodSignature = joinPoint.signature as MethodSignature
        val annotation = methodSignature.method.getAnnotation(EventLock::class.java)

        val name = annotation?.name ?: ""
        val lock = redissonClient.getLock(generateKey(name, request.targetId))
        try {
            //획득시도 시간, 락 점유 시간
            val available = lock.tryLock(5, 1, TimeUnit.SECONDS)

            if (!available) {
                println("lock 획득 실패")
                return
            }
            joinPoint.proceed()
        } finally {
            lock.unlock()
        }
    }

    private fun generateKey(name: String, id: Long): String {
        return "$name $id";
    }
}
package com.a03.concurrencycontrolproject.common.aop.redisson.spinlock

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
class RedissonSpinLockAspect(
    private val redissonClient: RedissonClient,
) {


    @Around("@annotation(com.a03.concurrencycontrolproject.common.aop.redisson.spinlock.RedissonSpinLock) && args(..,request)")
    fun run(joinPoint: ProceedingJoinPoint, request: ConcurrencyControlDto) {
        val methodSignature = joinPoint.signature as MethodSignature
        val annotation = methodSignature.method.getAnnotation(RedissonSpinLock::class.java)

        val name = annotation?.name ?: ""
        val lock = redissonClient.getSpinLock(generateKey(name, request.targetId))
        try {
            lock.tryLock(100, 100, TimeUnit.SECONDS)
            joinPoint.proceed()
        } finally {
            lock.unlock()
        }
    }

    private fun generateKey(name: String, id: Long): String {
        return "$name $id"
    }
}
package com.a03.concurrencycontrolproject.common.aop.spinlock

import com.a03.concurrencycontrolproject.common.aop.ConcurrencyControlDto
import com.a03.concurrencycontrolproject.common.redis.repository.RedisLockRepository
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component


@Aspect
@Component
class SpinLockAspect(
    private val redisLockRepository: RedisLockRepository
) {


    @Around("@annotation(com.a03.concurrencycontrolproject.common.aop.spinlock.SpinLock) && args(..,request)")
    fun run(joinPoint: ProceedingJoinPoint, request: ConcurrencyControlDto) {
        val methodSignature = joinPoint.signature as MethodSignature
        val annotation = methodSignature.method.getAnnotation(SpinLock::class.java)

        val name = annotation?.name ?: ""
        try {
            while (!redisLockRepository.lock(name, request.targetId)) {
                Thread.sleep(100)
            }
            joinPoint.proceed()
        } finally {
            redisLockRepository.unlock(name, request.targetId)
        }
    }
}
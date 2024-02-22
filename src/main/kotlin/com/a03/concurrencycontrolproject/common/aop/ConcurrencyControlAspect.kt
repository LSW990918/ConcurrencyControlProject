package com.a03.concurrencycontrolproject.common.aop

import com.a03.concurrencycontrolproject.common.redis.repository.RedisLockRepository
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component


@Aspect
@Component
class ConcurrencyControlAspect(
    private val redisLockRepository: RedisLockRepository
) {


    @Around("@annotation(com.a03.concurrencycontrolproject.common.aop.ConcurrencyControl) && args(..,request)")
    fun run(joinPoint: ProceedingJoinPoint, request: ConcurrencyControlDto) {
        val methodSignature = joinPoint.signature as MethodSignature
        val annotation = methodSignature.method.getAnnotation(ConcurrencyControl::class.java)

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
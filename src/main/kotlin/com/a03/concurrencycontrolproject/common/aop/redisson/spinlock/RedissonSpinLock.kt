package com.a03.concurrencycontrolproject.common.aop.redisson.spinlock

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RedissonSpinLock(
    val name: String
)

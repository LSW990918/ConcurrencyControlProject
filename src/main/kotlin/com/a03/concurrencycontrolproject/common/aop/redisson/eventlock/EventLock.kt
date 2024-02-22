package com.a03.concurrencycontrolproject.common.aop.redisson.eventlock

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class EventLock(
    val name: String
)

package com.a03.concurrencycontrolproject.common.aop.spinlock

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SpinLock(
    val name: String
)
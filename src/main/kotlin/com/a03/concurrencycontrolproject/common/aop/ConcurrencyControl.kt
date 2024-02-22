package com.a03.concurrencycontrolproject.common.aop

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConcurrencyControl (
    val name: String
)
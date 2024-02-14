package com.a03.concurrencycontrolproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConcurrencyControlProjectApplication

fun main(args: Array<String>) {
    runApplication<ConcurrencyControlProjectApplication>(*args)
}

package com.a03.concurrencycontrolproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class ConcurrencyControlProjectApplication

fun main(args: Array<String>) {
    runApplication<ConcurrencyControlProjectApplication>(*args)
}

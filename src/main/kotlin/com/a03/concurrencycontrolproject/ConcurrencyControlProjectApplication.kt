package com.a03.concurrencycontrolproject

import com.a03.concurrencycontrolproject.common.redis.RedisConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication(exclude = [RedisAutoConfiguration::class])
@EnableJpaAuditing
@EnableCaching
class ConcurrencyControlProjectApplication

fun main(args: Array<String>) {
    runApplication<ConcurrencyControlProjectApplication>(*args)
}

package com.a03.concurrencycontrolproject.common.redis.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository


@Repository
class RedisLockRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun lock(name: String, id: Long): Boolean {
        return redisTemplate
            .opsForValue()
            .setIfAbsent(generateKey(name, id), "Lock", java.time.Duration.ofMillis(3000))!!
    }

    fun unlock(name: String, id: Long): Boolean {
        return redisTemplate.delete(generateKey(name, id));
    }

    private fun generateKey(name: String, id: Long): String {
        return "$name $id";
    }
}
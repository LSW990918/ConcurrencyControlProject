package com.a03.concurrencycontrolproject.common.redis.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository


@Repository
class RedisLockRepository(
    private val redisTemplate: RedisTemplate<String,String>
) {
    fun lock(id: Long): Boolean {
        return redisTemplate
            .opsForValue()
            .setIfAbsent(generateKey(id), "lock", java.time.Duration.ofMillis(3000))!!
    }

    fun unlock(id: Long): Boolean {
        return redisTemplate.delete(generateKey(id));
    }

    private fun generateKey(id: Long): String {
        return id.toString();
    }
}
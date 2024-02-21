package com.a03.concurrencycontrolproject.common.redis

import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisConfig {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory()
    }

    @Bean
    fun redisTemplate(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisTemplate<String, String> {
        val template = RedisTemplate<String, String>()
        template.connectionFactory = redisConnectionFactory

        //String 자료구조를 위한 Serializer
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()

        //Hash 자료구조를 위한 Serializer
        template.hashKeySerializer = StringRedisSerializer()
        template.hashValueSerializer = StringRedisSerializer()

        return template
    }

    @Bean
    fun cacheManager(
        redisConnectionFactory: RedisConnectionFactory
    ): RedisCacheManager {
        val defaults: RedisCacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .entryTtl(Duration.ofMinutes(30)) //캐시 유효기간을 30분으로 설정

        return RedisCacheManager
            .RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory)
            .cacheDefaults(defaults).build()
    }


//
//    @Bean
//    fun redisConnectionFactory(): RedisConnectionFactory =
//        LettuceConnectionFactory()
//
//    @Bean
//    fun redisTemplate():RedisTemplate<String,String>{
//        return RedisTemplate<String,String>().apply{
//            connectionFactory = redisConnectionFactory()
//            keySerializer = StringRedisSerializer()
//            valueSerializer = StringRedisSerializer()
//        }
//    }
}


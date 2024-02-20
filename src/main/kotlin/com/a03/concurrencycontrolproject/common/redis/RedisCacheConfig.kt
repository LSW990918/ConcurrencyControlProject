package com.a03.concurrencycontrolproject.common.redis

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.hibernate.internal.HEMLogging.logger
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.cache.Cache
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.CacheErrorHandler
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching
@ConditionalOnProperty(name = ["cache.redis.enabled"], havingValue = "true")
class RedisCacheConfig(
    private val redisConnectionFactory: RedisConnectionFactory
) : CachingConfigurer {

    // Redis 캐시 정책 설정
    fun redisCacheConfiguration(): RedisCacheConfiguration {
        val config = RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer())
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer(jacksonObjectMapper())
                )
            )
//            .prefixCacheNameWith()  // Redis key prefix 기본 값 설정
            .entryTtl(Duration.ofMinutes(5)) // Redis TTL 기본 값 설정
        return RedisCacheConfiguration.defaultCacheConfig()
    }

    // 캐시 적용
    override fun cacheManager(): CacheManager = RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory)
        .cacheDefaults(redisCacheConfiguration())
        .build()

    // Exception 정책 설정
    // Exception일 때 기본 정책은 `throw Exception`이나, Exception이 생기면 missed 처리하기 위함
//    override fun errorHandler(): CacheErrorHandler {
//        return object : CacheErrorHandler {
//            override fun handleCacheGetError(exception: RuntimeException, cache: Cache, key: Any) {
//                logger.warn("[REDIS_CACHE:GET:${cache.name}]: ${exception.message}")
//            }
//
//            override fun handleCachePutError(exception: RuntimeException, cache: Cache, key: Any, value: Any?) {
//                logger.warn("[REDIS_CACHE:PUT:${cache.name}]: ${exception.message}")
//            }
//
//            override fun handleCacheEvictError(exception: RuntimeException, cache: Cache, key: Any) {
//                logger.warn("[REDIS_CACHE:EVICT:${cache.name}]: ${exception.message}")
//            }
//
//            override fun handleCacheClearError(exception: RuntimeException, cache: Cache) {
//                logger.warn("[REDIS_CACHE:CLEAR:${cache.name}]: ${exception.message}")
//            }
//
//        }
}



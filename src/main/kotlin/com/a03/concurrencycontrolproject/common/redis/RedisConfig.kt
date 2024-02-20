import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

//package com.a03.concurrencycontrolproject.common.redis
//
//import io.lettuce.core.ClientOptions
//import io.lettuce.core.TimeoutOptions
//import io.lettuce.core.resource.DefaultClientResources
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.cache.annotation.CacheConfig
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Import
//import org.springframework.data.redis.connection.RedisConfiguration
//import org.springframework.data.redis.connection.RedisConnectionFactory
//import org.springframework.data.redis.connection.RedisPassword
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
//import org.springframework.data.redis.core.RedisCallback
//import org.springframework.data.redis.core.RedisTemplate
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
//import org.springframework.data.redis.serializer.StringRedisSerializer
//import java.time.Duration
//
//@Configuration
//@ConditionalOnProperty(name = ["cache.redis.enabled"], havingValue = "true")
//@ConfigurationProperties(prefix = "spring.redis")
//@Import(RedisConfiguration::class)
//class RedisConfig {
//    private val host: String = "ec2-3-39-34-103.ap-northeast-2.compute.amazonaws.com"
//    private val port: Int = 6379
//    private val password: String? = null
//
//
//    @Bean
//    fun clientOptions(): ClientOptions = ClientOptions.builder()
//        .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
//        .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(1)))
//        .build()
//
//    @Bean
//    fun lettucePoolingClientConfiguration(clientOptions: ClientOptions) =
//        LettuceClientConfiguration.builder()
//            .clientOptions(clientOptions)
//            .clientResources(DefaultClientResources.create())
//            .build()
//
//    @Bean
//    fun redisStandaloneConfiguration() = RedisStandaloneConfiguration(host, port)
//        .apply {
//            if (this@RedisConfig.password != null) {
//                this.password = RedisPassword.of(this@RedisConfig.password)
//            }
//        }
//
//    @Bean
//    fun redisConnectionFactory(
//        redisStandaloneConfiguration: RedisStandaloneConfiguration,
//        lettucePoolingClientConfiguration: LettuceClientConfiguration
//    ): RedisConnectionFactory = LettuceConnectionFactory(redisStandaloneConfiguration, lettucePoolingClientConfiguration)
//
//    @Bean
//    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
//        val template = object : RedisTemplate<String, Any>() {
//            override fun <T : Any?> execute(  // redisTemplate을 활용한 캐싱할 경우 Exception이 생기면 null을 통한 missed 처리하기 위함
//                action: RedisCallback<T>,
//                exposeConnection: Boolean,
//                pipeline: Boolean
//            ): T? {
//                return runCatching {
//                    super.execute(action, exposeConnection, pipeline)
//                }.getOrElse {
//                    logger.warn(it.localizedMessage)
//                    null
//                }
//            }
//        }.apply {
//            this.isEnableDefaultSerializer = false
//            this.keySerializer = StringRedisSerializer()
//            this.valueSerializer = GenericJackson2JsonRedisSerializer(CacheConfig.objectMapper)
//        }
//
//        template.setConnectionFactory(redisConnectionFactory)
//        return template
//    }
//}

@Configuration
class RedisConfig {

    @Bean
    fun lettuceConnectionFactory(): LettuceConnectionFactory { // Lettuce 기능 사용
        val lettuceClientConfiguration = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ZERO)
            .shutdownTimeout(Duration.ZERO)
            .build()
        val redisStandaloneConfiguration =
            RedisStandaloneConfiguration(hostName = "ec2-3-39-34-103.ap-northeast-2.compute.amazonaws.com", port = 6379)
        return LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        return RedisTemplate<Any, Any>().apply {
            this.connectionFactory = lettuceConnectionFactory()

            // "\xac\xed\x00" 같은 불필요한 해시값을 보지 않기 위해 serializer 설정
            this.keySerializer = StringRedisSerializer()
            this.hashKeySerializer = StringRedisSerializer()
            this.valueSerializer = StringRedisSerializer()
        }
    }

    @Bean
    fun cacheManager(): CacheManager {
        val configuration = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer()
                )
            ) // Serialize 관련 설정
            .entryTtl(Duration.ofMinutes(2)) // 캐시 기본 ttl 2분 설정
            .disableCachingNullValues() // Null 캐싱 제외
        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(lettuceConnectionFactory())
            .cacheDefaults(configuration)
            .build()
    }
}
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository


@Repository
class RedisLockRepository(
    private val redisTemplate: RedisTemplate<String,String>
) {
    fun lock(userId: Long): Boolean {
        return redisTemplate
            .opsForValue()
            .setIfAbsent(generateKey(userId), "lock", java.time.Duration.ofMillis(3000))!!
    }

    fun unlock(userId: Long): Boolean {
        return redisTemplate.delete(generateKey(userId));
    }

    private fun generateKey(userId: Long): String {
        return userId.toString();
    }
}
package com.groom.product.common.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile

/**
 * Test용 Redisson 설정
 *
 * Testcontainers Redis와 연동하여 Redisson 클라이언트를 제공합니다.
 */
@TestConfiguration
@Profile("test")
class TestRedissonConfig {
    @Bean
    fun redissonClient(
        @Value("\${spring.redis.host:localhost}") host: String,
        @Value("\${spring.redis.port:6379}") port: Int,
    ): RedissonClient {
        val config = Config()
        config
            .useSingleServer()
            .setAddress("redis://$host:$port")
            .setConnectionPoolSize(10)
            .setConnectionMinimumIdleSize(2)
            .setTimeout(3000)
            .setRetryAttempts(3)
            .setRetryInterval(1500)

        return Redisson.create(config)
    }
}

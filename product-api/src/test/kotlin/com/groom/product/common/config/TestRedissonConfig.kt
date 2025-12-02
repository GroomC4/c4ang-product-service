package com.groom.product.common.config

import com.groom.platform.testcontainers.container.SharedContainers
import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

/**
 * 테스트 환경 전용 Redisson 설정
 *
 * SharedContainers에서 Redis 컨테이너 정보를 직접 가져옵니다.
 * 이렇게 하면 Testcontainers가 할당한 동적 포트를 사용할 수 있습니다.
 *
 * 프로덕션/개발 환경에서는 RedissonConfig가 사용됩니다.
 */
@Profile("test")
@Configuration
class TestRedissonConfig {
    @Bean
    fun redissonClient(): RedissonClient {
        val redisContainer = SharedContainers.redisContainer
        val redisHost = redisContainer.host
        val redisPort = redisContainer.getMappedPort(6379)

        val config = Config()

        config
            .useSingleServer()
            .setAddress("redis://$redisHost:$redisPort")
            .setConnectionPoolSize(10)
            .setConnectionMinimumIdleSize(2)
            .setTimeout(3000) // 3초
            .setRetryAttempts(3)
            .setRetryInterval(1500) // 1.5초

        return Redisson.create(config)
    }
}

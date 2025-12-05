package com.groom.product.adapter.outbound.scheduler

import net.javacrumbs.shedlock.core.LockProvider
import net.javacrumbs.shedlock.provider.redis.spring.RedisLockProvider
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * ShedLock 설정
 *
 * 분산 환경에서 스케줄러 중복 실행을 방지합니다.
 * Redis를 Lock Provider로 사용합니다.
 *
 * Lock Key 형식: job-lock:{lockName}
 * 예: job-lock:StockReservationScheduler.processExpiredReservations
 */
@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "10m")
class ShedLockConfig {
    @Bean
    fun lockProvider(connectionFactory: RedisConnectionFactory): LockProvider = RedisLockProvider(connectionFactory, "product-service")
}

package com.groom.product.common.config

import com.groom.product.common.domain.DomainEvent
import com.groom.product.common.domain.DomainEventPublisher
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary

@TestConfiguration
class NoOpEventPublisherConfig {

    @Bean
    @Primary
    fun domainEventPublisher(): DomainEventPublisher {
        return object : DomainEventPublisher {
            override fun publish(event: DomainEvent) {
                println("NoOpEventPublisher: Event publishing is disabled in tests. Event: ${event.javaClass.simpleName}")
            }
        }
    }
}

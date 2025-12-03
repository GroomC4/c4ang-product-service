package com.groom.product.adapter.outbound.redis

import com.groom.product.domain.port.StockReservationPort
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.UUID
import java.util.concurrent.TimeUnit

/**
 * Redis를 사용한 재고 예약 Adapter.
 *
 * Redisson 클라이언트를 사용하여 원자적 재고 예약 연산을 수행합니다.
 */
@Component
class RedisStockReservationAdapter(
    private val redissonClient: RedissonClient,
) : StockReservationPort {
    override fun getOrInitializeStock(
        productId: UUID,
        initialStock: Int,
    ): Long {
        val stockKey = "stock:$productId"
        val atomicLong = redissonClient.getAtomicLong(stockKey)
        if (!atomicLong.isExists) {
            atomicLong.set(initialStock.toLong())
        }
        return atomicLong.get()
    }

    override fun decrementStock(
        productId: UUID,
        quantity: Int,
    ): Long {
        val stockKey = "stock:$productId"
        val atomicLong = redissonClient.getAtomicLong(stockKey)
        return atomicLong.addAndGet(-quantity.toLong())
    }

    override fun incrementStock(
        productId: UUID,
        quantity: Int,
    ): Long {
        val stockKey = "stock:$productId"
        val atomicLong = redissonClient.getAtomicLong(stockKey)
        return atomicLong.addAndGet(quantity.toLong())
    }

    override fun saveReservation(
        orderId: UUID,
        productId: UUID,
        quantity: Int,
        ttl: Long,
        timeUnit: TimeUnit,
    ) {
        val reservationKey = "stock:reservation:$orderId:$productId"
        val reservationBucket = redissonClient.getBucket<Int>(reservationKey)
        val duration = Duration.of(ttl, timeUnit.toChronoUnit())
        reservationBucket.set(quantity, duration)
    }

    override fun deleteReservation(
        orderId: UUID,
        productId: UUID,
    ) {
        val reservationKey = "stock:reservation:$orderId:$productId"
        redissonClient.getBucket<Int>(reservationKey).delete()
    }
}

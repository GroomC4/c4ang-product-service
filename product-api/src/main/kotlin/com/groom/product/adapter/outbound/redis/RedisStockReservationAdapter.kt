package com.groom.product.adapter.outbound.redis

import com.groom.product.domain.port.StockReservationPort
import io.github.oshai.kotlinlogging.KotlinLogging
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.UUID
import java.util.concurrent.TimeUnit

private val logger = KotlinLogging.logger {}

/**
 * Redis를 사용한 재고 예약 Adapter.
 *
 * Redisson 클라이언트를 사용하여 원자적 재고 예약 연산을 수행합니다.
 *
 * Redis Key 구조:
 * - stock:{productId} → 재고 수량 (AtomicLong)
 * - stock:reservation:{orderId}:{productId} → 예약 수량 (Bucket, TTL)
 * - stock:reservation-expiry-index → 만료 인덱스 (ScoredSortedSet)
 */
@Component
class RedisStockReservationAdapter(
    private val redissonClient: RedissonClient,
) : StockReservationPort {
    companion object {
        private const val STOCK_KEY_PREFIX = "stock:"
        private const val RESERVATION_KEY_PREFIX = "stock:reservation:"
        private const val EXPIRY_INDEX_KEY = "stock:reservation-expiry-index"
    }

    override fun getOrInitializeStock(
        productId: UUID,
        initialStock: Int,
    ): Long {
        val stockKey = "$STOCK_KEY_PREFIX$productId"
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
        val stockKey = "$STOCK_KEY_PREFIX$productId"
        val atomicLong = redissonClient.getAtomicLong(stockKey)
        return atomicLong.addAndGet(-quantity.toLong())
    }

    override fun incrementStock(
        productId: UUID,
        quantity: Int,
    ): Long {
        val stockKey = "$STOCK_KEY_PREFIX$productId"
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
        val reservationKey = "$RESERVATION_KEY_PREFIX$orderId:$productId"
        val reservationBucket = redissonClient.getBucket<Int>(reservationKey)
        val duration = Duration.of(ttl, timeUnit.toChronoUnit())
        reservationBucket.set(quantity, duration)
    }

    override fun deleteReservation(
        orderId: UUID,
        productId: UUID,
    ) {
        val reservationKey = "$RESERVATION_KEY_PREFIX$orderId:$productId"
        redissonClient.getBucket<Int>(reservationKey).delete()
    }

    override fun getReservation(
        orderId: UUID,
        productId: UUID,
    ): Int? {
        val reservationKey = "$RESERVATION_KEY_PREFIX$orderId:$productId"
        return redissonClient.getBucket<Int>(reservationKey).get()
    }

    override fun registerExpiry(
        orderId: UUID,
        productId: UUID,
        quantity: Int,
        expiresAtEpochSecond: Long,
    ) {
        val expirySet = redissonClient.getScoredSortedSet<String>(EXPIRY_INDEX_KEY)
        // 값 형식: orderId:productId:quantity
        val entry = "$orderId:$productId:$quantity"
        expirySet.add(expiresAtEpochSecond.toDouble(), entry)
        logger.debug { "Registered expiry: $entry at $expiresAtEpochSecond" }
    }

    override fun getExpiredEntries(nowEpochSecond: Long): Collection<String> {
        val expirySet = redissonClient.getScoredSortedSet<String>(EXPIRY_INDEX_KEY)
        return expirySet.valueRange(0.0, true, nowEpochSecond.toDouble(), true)
    }

    override fun removeExpiryEntry(entry: String) {
        val expirySet = redissonClient.getScoredSortedSet<String>(EXPIRY_INDEX_KEY)
        expirySet.remove(entry)
    }
}

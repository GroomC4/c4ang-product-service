package com.groom.product.adapter.outbound.scheduler

import com.groom.product.domain.port.StockReservationPort
import io.github.oshai.kotlinlogging.KotlinLogging
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.UUID

private val logger = KotlinLogging.logger {}

/**
 * 만료된 재고 예약을 자동으로 복구하는 스케줄러.
 *
 * 5분마다 실행되며, 만료된 예약의 재고를 복구합니다.
 * ShedLock을 사용하여 분산 환경에서 중복 실행을 방지합니다.
 *
 * Redis Key:
 * - stock:reservation-expiry-index (ScoredSortedSet)
 *   - score: 만료 시각 (epoch second)
 *   - value: "{orderId}:{productId}:{quantity}"
 */
@Component
class StockReservationScheduler(
    private val stockReservationPort: StockReservationPort,
) {
    /**
     * 만료된 재고 예약을 처리합니다.
     *
     * - 매 5분마다 실행
     * - ShedLock으로 분산 환경 중복 실행 방지
     * - lockAtMostFor: 최대 9분간 락 유지 (스케줄러 간격보다 길게)
     * - lockAtLeastFor: 최소 30초간 락 유지 (빈번한 실행 방지)
     */
    @Scheduled(cron = "0 */5 * * * *")
    @SchedulerLock(
        name = "StockReservationScheduler.processExpiredReservations",
        lockAtMostFor = "9m",
        lockAtLeastFor = "30s",
    )
    fun processExpiredReservations() {
        logger.info { "Starting expired stock reservation cleanup..." }

        try {
            val nowEpochSecond = Instant.now().epochSecond
            val expiredEntries = stockReservationPort.getExpiredEntries(nowEpochSecond)

            if (expiredEntries.isEmpty()) {
                logger.debug { "No expired reservations to process" }
                return
            }

            logger.info { "Found ${expiredEntries.size} expired reservations to process" }

            var processedCount = 0

            for (entry in expiredEntries) {
                try {
                    val parts = entry.split(":")
                    if (parts.size != 3) {
                        logger.warn { "Invalid expiry entry format: $entry" }
                        stockReservationPort.removeExpiryEntry(entry)
                        continue
                    }

                    val orderId = UUID.fromString(parts[0])
                    val productId = UUID.fromString(parts[1])
                    val quantity = parts[2].toInt()

                    // 예약이 아직 존재하는지 확인 (이미 확정/취소된 경우 스킵)
                    val existingReservation = stockReservationPort.getReservation(orderId, productId)
                    if (existingReservation != null) {
                        // 재고 복구
                        stockReservationPort.incrementStock(productId, quantity)
                        // 예약 삭제
                        stockReservationPort.deleteReservation(orderId, productId)
                        logger.info { "Restored stock for expired reservation: orderId=$orderId, productId=$productId, quantity=$quantity" }
                    } else {
                        logger.debug { "Reservation already processed (confirmed/cancelled): orderId=$orderId, productId=$productId" }
                    }

                    // 만료 인덱스에서 삭제
                    stockReservationPort.removeExpiryEntry(entry)
                    processedCount++
                } catch (e: Exception) {
                    logger.error(e) { "Failed to process expired reservation: $entry" }
                }
            }

            logger.info { "Completed expired stock reservation cleanup: $processedCount reservations processed" }
        } catch (e: Exception) {
            logger.error(e) { "Failed to process expired reservations" }
            // 예외를 삼켜서 스케줄러가 계속 실행될 수 있도록 함
        }
    }
}

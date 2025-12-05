package com.groom.product.adapter.outbound.scheduler

import com.groom.product.common.annotation.UnitTest
import com.groom.product.domain.port.StockReservationPort
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import java.util.UUID

@UnitTest
class StockReservationSchedulerTest :
    BehaviorSpec({

        Given("만료된 예약 처리 스케줄러가 실행될 때") {

            When("만료된 예약이 없는 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val scheduler = StockReservationScheduler(stockReservationPort)
                every { stockReservationPort.getExpiredEntries(any()) } returns emptySet()

                Then("아무 처리도 하지 않는다") {
                    scheduler.processExpiredReservations()
                    verify { stockReservationPort.getExpiredEntries(any()) }
                    verify(exactly = 0) { stockReservationPort.getReservation(any(), any()) }
                    verify(exactly = 0) { stockReservationPort.incrementStock(any(), any()) }
                    verify(exactly = 0) { stockReservationPort.deleteReservation(any(), any()) }
                }
            }

            When("만료된 예약이 있고 아직 확정/취소되지 않은 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val scheduler = StockReservationScheduler(stockReservationPort)
                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val expiredEntry = "$testOrderId:$testProductId:5"

                every { stockReservationPort.getExpiredEntries(any()) } returns setOf(expiredEntry)
                every { stockReservationPort.getReservation(testOrderId, testProductId) } returns 5
                every { stockReservationPort.incrementStock(testProductId, 5) } returns 105L
                every { stockReservationPort.deleteReservation(testOrderId, testProductId) } just runs
                every { stockReservationPort.removeExpiryEntry(expiredEntry) } just runs

                Then("재고를 복구하고 예약을 삭제한다") {
                    scheduler.processExpiredReservations()
                    verify { stockReservationPort.getReservation(testOrderId, testProductId) }
                    verify { stockReservationPort.incrementStock(testProductId, 5) }
                    verify { stockReservationPort.deleteReservation(testOrderId, testProductId) }
                    verify { stockReservationPort.removeExpiryEntry(expiredEntry) }
                }
            }

            When("만료된 예약이 이미 확정/취소된 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val scheduler = StockReservationScheduler(stockReservationPort)
                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val expiredEntry = "$testOrderId:$testProductId:5"

                every { stockReservationPort.getExpiredEntries(any()) } returns setOf(expiredEntry)
                every { stockReservationPort.getReservation(testOrderId, testProductId) } returns null
                every { stockReservationPort.removeExpiryEntry(expiredEntry) } just runs

                Then("재고 복구 없이 만료 인덱스만 삭제한다") {
                    scheduler.processExpiredReservations()
                    verify { stockReservationPort.getReservation(testOrderId, testProductId) }
                    verify(exactly = 0) { stockReservationPort.incrementStock(any(), any()) }
                    verify(exactly = 0) { stockReservationPort.deleteReservation(any(), any()) }
                    verify { stockReservationPort.removeExpiryEntry(expiredEntry) }
                }
            }

            When("여러 개의 만료된 예약이 있는 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val scheduler = StockReservationScheduler(stockReservationPort)
                val testOrderId1 = UUID.randomUUID()
                val testProductId1 = UUID.randomUUID()
                val testOrderId2 = UUID.randomUUID()
                val testProductId2 = UUID.randomUUID()
                val expiredEntry1 = "$testOrderId1:$testProductId1:5"
                val expiredEntry2 = "$testOrderId2:$testProductId2:3"

                every { stockReservationPort.getExpiredEntries(any()) } returns setOf(expiredEntry1, expiredEntry2)
                every { stockReservationPort.getReservation(testOrderId1, testProductId1) } returns 5
                every { stockReservationPort.getReservation(testOrderId2, testProductId2) } returns 3
                every { stockReservationPort.incrementStock(testProductId1, 5) } returns 105L
                every { stockReservationPort.incrementStock(testProductId2, 3) } returns 53L
                every { stockReservationPort.deleteReservation(any(), any()) } just runs
                every { stockReservationPort.removeExpiryEntry(any()) } just runs

                Then("모든 만료된 예약을 처리한다") {
                    scheduler.processExpiredReservations()
                    verify { stockReservationPort.incrementStock(testProductId1, 5) }
                    verify { stockReservationPort.incrementStock(testProductId2, 3) }
                    verify { stockReservationPort.deleteReservation(testOrderId1, testProductId1) }
                    verify { stockReservationPort.deleteReservation(testOrderId2, testProductId2) }
                    verify { stockReservationPort.removeExpiryEntry(expiredEntry1) }
                    verify { stockReservationPort.removeExpiryEntry(expiredEntry2) }
                }
            }

            When("잘못된 형식의 엔트리가 있는 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val scheduler = StockReservationScheduler(stockReservationPort)
                val invalidEntry = "invalid-format"

                every { stockReservationPort.getExpiredEntries(any()) } returns setOf(invalidEntry)
                every { stockReservationPort.removeExpiryEntry(invalidEntry) } just runs

                Then("해당 엔트리만 삭제하고 계속 진행한다") {
                    scheduler.processExpiredReservations()
                    verify { stockReservationPort.removeExpiryEntry(invalidEntry) }
                    verify(exactly = 0) { stockReservationPort.getReservation(any(), any()) }
                }
            }

            When("일부 예약은 유효하고 일부는 잘못된 형식인 경우") {
                val stockReservationPort = mockk<StockReservationPort>(relaxed = true)
                val scheduler = StockReservationScheduler(stockReservationPort)
                val testOrderId = UUID.randomUUID()
                val testProductId = UUID.randomUUID()
                val validEntry = "$testOrderId:$testProductId:5"
                val invalidEntry = "invalid"

                every { stockReservationPort.getExpiredEntries(any()) } returns setOf(validEntry, invalidEntry)
                every { stockReservationPort.getReservation(testOrderId, testProductId) } returns 5
                every { stockReservationPort.incrementStock(testProductId, 5) } returns 105L
                every { stockReservationPort.deleteReservation(any(), any()) } just runs
                every { stockReservationPort.removeExpiryEntry(any()) } just runs

                Then("유효한 예약은 처리하고 잘못된 엔트리는 삭제한다") {
                    scheduler.processExpiredReservations()
                    verify { stockReservationPort.incrementStock(testProductId, 5) }
                    verify { stockReservationPort.deleteReservation(testOrderId, testProductId) }
                    verify { stockReservationPort.removeExpiryEntry(validEntry) }
                    verify { stockReservationPort.removeExpiryEntry(invalidEntry) }
                }
            }
        }
    })

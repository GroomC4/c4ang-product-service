package com.groom.product.adapter.outbound.redis

import com.groom.product.common.annotation.UnitTest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.redisson.api.RAtomicLong
import org.redisson.api.RBucket
import org.redisson.api.RScoredSortedSet
import org.redisson.api.RedissonClient
import java.time.Duration
import java.util.UUID
import java.util.concurrent.TimeUnit

@UnitTest
class RedisStockReservationAdapterTest :
    BehaviorSpec({

        Given("재고 조회 및 초기화가 필요할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testProductId = UUID.randomUUID()

            When("재고가 존재하지 않는 경우") {
                val atomicLong = mockk<RAtomicLong>(relaxed = true)
                every { redissonClient.getAtomicLong("stock:$testProductId") } returns atomicLong
                every { atomicLong.isExists } returns false
                every { atomicLong.get() } returns 100L

                Then("초기값으로 설정하고 반환한다") {
                    val result = adapter.getOrInitializeStock(testProductId, 100)
                    result shouldBe 100L
                    verify { atomicLong.set(100L) }
                }
            }

            When("재고가 이미 존재하는 경우") {
                val atomicLong = mockk<RAtomicLong>(relaxed = true)
                every { redissonClient.getAtomicLong("stock:$testProductId") } returns atomicLong
                every { atomicLong.isExists } returns true
                every { atomicLong.get() } returns 50L

                Then("기존 재고를 반환한다") {
                    val result = adapter.getOrInitializeStock(testProductId, 100)
                    result shouldBe 50L
                    verify(exactly = 0) { atomicLong.set(any()) }
                }
            }
        }

        Given("재고 차감이 필요할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testProductId = UUID.randomUUID()

            When("수량만큼 재고를 차감하면") {
                val atomicLong = mockk<RAtomicLong>(relaxed = true)
                every { redissonClient.getAtomicLong("stock:$testProductId") } returns atomicLong
                every { atomicLong.addAndGet(-10L) } returns 90L

                Then("차감 후 남은 재고를 반환한다") {
                    val result = adapter.decrementStock(testProductId, 10)
                    result shouldBe 90L
                }
            }

            When("재고보다 많은 수량을 차감하면") {
                val atomicLong = mockk<RAtomicLong>(relaxed = true)
                every { redissonClient.getAtomicLong("stock:$testProductId") } returns atomicLong
                every { atomicLong.addAndGet(-150L) } returns -50L

                Then("음수 재고를 반환한다") {
                    val result = adapter.decrementStock(testProductId, 150)
                    result shouldBe -50L
                }
            }
        }

        Given("재고 증가가 필요할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testProductId = UUID.randomUUID()
            val atomicLong = mockk<RAtomicLong>(relaxed = true)
            every { redissonClient.getAtomicLong("stock:$testProductId") } returns atomicLong
            every { atomicLong.addAndGet(10L) } returns 110L

            When("수량만큼 재고를 증가하면") {
                Then("증가 후 재고를 반환한다") {
                    val result = adapter.incrementStock(testProductId, 10)
                    result shouldBe 110L
                }
            }
        }

        Given("예약 정보를 저장할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testOrderId = UUID.randomUUID()
            val testProductId = UUID.randomUUID()
            val bucket = mockk<RBucket<Int>>(relaxed = true)
            every { redissonClient.getBucket<Int>("stock:reservation:$testOrderId:$testProductId") } returns bucket

            When("TTL과 함께 예약을 저장하면") {
                Then("예약 정보가 TTL과 함께 저장된다") {
                    adapter.saveReservation(testOrderId, testProductId, 5, 15, TimeUnit.MINUTES)
                    verify { bucket.set(5, Duration.ofMinutes(15)) }
                }
            }
        }

        Given("예약 정보를 조회할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testOrderId = UUID.randomUUID()
            val testProductId = UUID.randomUUID()

            When("예약이 존재하는 경우") {
                val bucket = mockk<RBucket<Int>>(relaxed = true)
                every { redissonClient.getBucket<Int>("stock:reservation:$testOrderId:$testProductId") } returns bucket
                every { bucket.get() } returns 5

                Then("예약 수량을 반환한다") {
                    val result = adapter.getReservation(testOrderId, testProductId)
                    result shouldBe 5
                }
            }

            When("예약이 존재하지 않는 경우") {
                val bucket = mockk<RBucket<Int>>(relaxed = true)
                every { redissonClient.getBucket<Int>("stock:reservation:$testOrderId:$testProductId") } returns bucket
                every { bucket.get() } returns null

                Then("null을 반환한다") {
                    val result = adapter.getReservation(testOrderId, testProductId)
                    result shouldBe null
                }
            }
        }

        Given("예약 정보를 삭제할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testOrderId = UUID.randomUUID()
            val testProductId = UUID.randomUUID()
            val bucket = mockk<RBucket<Int>>(relaxed = true)
            every { redissonClient.getBucket<Int>("stock:reservation:$testOrderId:$testProductId") } returns bucket

            When("예약을 삭제하면") {
                Then("예약 정보가 삭제된다") {
                    adapter.deleteReservation(testOrderId, testProductId)
                    verify { bucket.delete() }
                }
            }
        }

        Given("만료 인덱스에 예약을 등록할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testOrderId = UUID.randomUUID()
            val testProductId = UUID.randomUUID()
            val scoredSet = mockk<RScoredSortedSet<String>>(relaxed = true)
            every { redissonClient.getScoredSortedSet<String>("stock:reservation-expiry-index") } returns scoredSet

            When("만료 시각과 함께 등록하면") {
                val expiresAt = 1700000000L

                Then("ScoredSortedSet에 추가된다") {
                    adapter.registerExpiry(testOrderId, testProductId, 5, expiresAt)
                    verify { scoredSet.add(expiresAt.toDouble(), "$testOrderId:$testProductId:5") }
                }
            }
        }

        Given("만료된 예약을 조회할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testOrderId = UUID.randomUUID()
            val testProductId = UUID.randomUUID()

            When("만료된 엔트리가 있는 경우") {
                val scoredSet = mockk<RScoredSortedSet<String>>(relaxed = true)
                every { redissonClient.getScoredSortedSet<String>("stock:reservation-expiry-index") } returns scoredSet
                val nowEpochSecond = 1700000100L
                val expiredEntry1 = "$testOrderId:$testProductId:5"
                val expiredEntry2 = "${UUID.randomUUID()}:${UUID.randomUUID()}:3"

                every {
                    scoredSet.valueRange(0.0, true, nowEpochSecond.toDouble(), true)
                } returns setOf(expiredEntry1, expiredEntry2)

                Then("만료된 엔트리 목록을 반환한다") {
                    val result = adapter.getExpiredEntries(nowEpochSecond)
                    result shouldHaveSize 2
                    result shouldContain expiredEntry1
                    result shouldContain expiredEntry2
                }
            }

            When("만료된 엔트리가 없는 경우") {
                val scoredSet = mockk<RScoredSortedSet<String>>(relaxed = true)
                every { redissonClient.getScoredSortedSet<String>("stock:reservation-expiry-index") } returns scoredSet
                val nowEpochSecond = 1700000100L
                every {
                    scoredSet.valueRange(0.0, true, nowEpochSecond.toDouble(), true)
                } returns emptySet()

                Then("빈 컬렉션을 반환한다") {
                    val result = adapter.getExpiredEntries(nowEpochSecond)
                    result.shouldBeEmpty()
                }
            }
        }

        Given("만료 인덱스에서 엔트리를 삭제할 때") {
            val redissonClient = mockk<RedissonClient>()
            val adapter = RedisStockReservationAdapter(redissonClient)
            val testOrderId = UUID.randomUUID()
            val testProductId = UUID.randomUUID()
            val scoredSet = mockk<RScoredSortedSet<String>>(relaxed = true)
            every { redissonClient.getScoredSortedSet<String>("stock:reservation-expiry-index") } returns scoredSet
            val entry = "$testOrderId:$testProductId:5"

            When("엔트리를 삭제하면") {
                Then("ScoredSortedSet에서 제거된다") {
                    adapter.removeExpiryEntry(entry)
                    verify { scoredSet.remove(entry) }
                }
            }
        }
    })

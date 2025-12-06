package com.groom.product.domain.port

import java.util.UUID
import java.util.concurrent.TimeUnit

/**
 * 재고 예약을 위한 Outbound Port.
 *
 * Domain이 외부 인프라(Redis)에 요구하는 재고 예약 관련 계약입니다.
 * 특정 기술(Redisson 등)에 의존하지 않는 순수한 비즈니스 인터페이스입니다.
 */
interface StockReservationPort {
    /**
     * 현재 재고를 조회합니다. 캐시에 없으면 초기값을 설정합니다.
     *
     * @param productId 상품 ID
     * @param initialStock 캐시에 없을 경우 초기화할 재고 수량
     * @return 현재 재고 수량
     */
    fun getOrInitializeStock(
        productId: UUID,
        initialStock: Int,
    ): Long

    /**
     * 원자적으로 재고를 차감합니다.
     *
     * @param productId 상품 ID
     * @param quantity 차감할 수량
     * @return 차감 후 남은 재고
     */
    fun decrementStock(
        productId: UUID,
        quantity: Int,
    ): Long

    /**
     * 원자적으로 재고를 증가합니다 (롤백 시 사용).
     *
     * @param productId 상품 ID
     * @param quantity 증가할 수량
     * @return 증가 후 재고
     */
    fun incrementStock(
        productId: UUID,
        quantity: Int,
    ): Long

    /**
     * 예약 정보를 저장합니다.
     *
     * @param orderId 주문 ID
     * @param productId 상품 ID
     * @param quantity 예약 수량
     * @param ttl 만료 시간
     * @param timeUnit 시간 단위
     */
    fun saveReservation(
        orderId: UUID,
        productId: UUID,
        quantity: Int,
        ttl: Long,
        timeUnit: TimeUnit,
    )

    /**
     * 예약 정보를 삭제합니다.
     *
     * @param orderId 주문 ID
     * @param productId 상품 ID
     */
    fun deleteReservation(
        orderId: UUID,
        productId: UUID,
    )

    /**
     * 예약 정보를 조회합니다.
     *
     * @param orderId 주문 ID
     * @param productId 상품 ID
     * @return 예약된 수량 (없으면 null)
     */
    fun getReservation(
        orderId: UUID,
        productId: UUID,
    ): Int?

    /**
     * 만료 인덱스에 예약 정보를 등록합니다.
     *
     * @param orderId 주문 ID
     * @param productId 상품 ID
     * @param quantity 예약 수량
     * @param expiresAtEpochSecond 만료 시각 (epoch second)
     */
    fun registerExpiry(
        orderId: UUID,
        productId: UUID,
        quantity: Int,
        expiresAtEpochSecond: Long,
    )

    /**
     * 만료된 예약 엔트리들을 조회합니다.
     *
     * @param nowEpochSecond 현재 시각 (epoch second)
     * @return 만료된 예약 엔트리 목록 (orderId:productId:quantity 형식)
     */
    fun getExpiredEntries(nowEpochSecond: Long): Collection<String>

    /**
     * 만료 인덱스에서 엔트리를 삭제합니다.
     *
     * @param entry 삭제할 엔트리 (orderId:productId:quantity 형식)
     */
    fun removeExpiryEntry(entry: String)

    /**
     * 특정 주문의 모든 예약 정보를 조회합니다.
     *
     * 만료 인덱스에서 orderId로 시작하는 모든 엔트리를 찾아
     * productId와 quantity 정보를 반환합니다.
     *
     * @param orderId 주문 ID
     * @return 예약된 상품 정보 목록 (productId, quantity)
     */
    fun getReservationsByOrderId(orderId: UUID): List<ReservationInfo>

    /**
     * 예약 정보 데이터 클래스
     */
    data class ReservationInfo(
        val productId: UUID,
        val quantity: Int,
    )
}

package com.groom.product.application.service

import com.groom.product.common.exception.ProductException
import com.groom.product.domain.model.Perfume
import com.groom.product.domain.port.LoadPerfumePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * RAG(챗봇) 서비스를 위한 향수 조회 Application Service.
 *
 * RAG 서비스가 필요로 하는 향수 정보를 제공합니다.
 * Consumer-Driven Contract 패턴에 따라 RAG 서비스가 필요로 하는 필드를 반환합니다.
 */
@Service
@Transactional(readOnly = true)
class RagPerfumeService(
    private val loadPerfumePort: LoadPerfumePort,
) {
    /**
     * 향수 검색.
     *
     * productIds가 제공되면 다른 필터는 무시하고 해당 ID의 향수만 반환합니다.
     * RAG에서 벡터DB로 유사 향수 ID를 찾은 후, 이 메서드로 상세 정보를 조회합니다.
     *
     * @param productIds 특정 ID 목록 조회 (제공 시 다른 필터 무시)
     * @param brand 브랜드 필터
     * @param gender 성별 필터
     * @return 조건에 맞는 향수 목록
     */
    fun searchPerfumes(
        productIds: List<UUID>? = null,
        brand: String? = null,
        gender: String? = null,
    ): List<Perfume> {
        // productIds가 제공되면 다른 필터 무시
        if (!productIds.isNullOrEmpty()) {
            return loadPerfumePort.loadAllById(productIds)
        }

        // 브랜드 필터
        if (!brand.isNullOrBlank()) {
            return loadPerfumePort.loadByBrand(brand)
        }

        // 성별 필터
        if (!gender.isNullOrBlank()) {
            return loadPerfumePort.loadByGender(gender)
        }

        // 필터 없으면 전체 조회
        return loadPerfumePort.loadAll()
    }

    /**
     * 향수 상세 조회.
     *
     * @param perfumeId 향수 ID
     * @return 향수 상세 정보
     * @throws ProductException.ProductNotFound 향수를 찾을 수 없는 경우
     */
    fun getPerfumeDetail(perfumeId: UUID): Perfume =
        loadPerfumePort.loadById(perfumeId)
            ?: throw ProductException.ProductNotFound(perfumeId)

    /**
     * 향수 비교.
     *
     * 2~4개의 향수를 비교합니다.
     *
     * @param perfumeIds 비교할 향수 ID 목록 (2~4개)
     * @return 비교 대상 향수 목록
     */
    fun comparePerfumes(perfumeIds: List<UUID>): List<Perfume> {
        require(perfumeIds.size in 2..4) { "비교할 향수는 2~4개 사이여야 합니다." }
        return loadPerfumePort.loadAllById(perfumeIds)
    }
}

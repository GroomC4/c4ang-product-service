package com.groom.product.infrastructure.repository

import com.groom.product.domain.model.Product
import jakarta.persistence.criteria.Predicate
import org.springframework.data.jpa.domain.Specification
import java.math.BigDecimal

/**
 * Product 엔티티에 대한 Specification 정의.
 *
 * 동적 검색 조건 생성을 위한 유틸리티 클래스입니다.
 *
 * **유사도 검색:**
 * PostgreSQL의 pg_trgm 확장을 사용하여 텍스트 유사도 기반 검색을 지원합니다.
 * - similarity() 함수: 두 문자열의 유사도 점수 계산 (0~1)
 * - % 연산자: 유사도 임계값 이상인 레코드 필터링 (기본 0.3)
 */
object ProductSpecifications {
    /**
     * 상품명에 키워드가 포함된 상품 검색 (대소문자 구분 없음).
     */
    fun nameContains(keyword: String?): Specification<Product>? {
        if (keyword.isNullOrBlank()) return null
        return Specification { root, _, criteriaBuilder ->
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%${keyword.lowercase()}%",
            )
        }
    }

    /**
     * 상품명 유사도 검색 (PostgreSQL pg_trgm 확장 활용).
     *
     * similarity() 함수를 사용하여 키워드와 상품명의 유사도를 계산하고,
     * % 연산자로 임계값(0.3) 이상인 상품을 검색합니다.
     *
     * @param keyword 검색 키워드
     * @return 유사도 기반 검색 Specification
     */
    fun nameSimilarTo(keyword: String?): Specification<Product>? {
        if (keyword.isNullOrBlank()) return null
        return Specification { root, _, criteriaBuilder ->
            // PostgreSQL의 % 연산자 사용 (pg_trgm 유사도 검색)
            criteriaBuilder.greaterThanOrEqualTo(
                criteriaBuilder.function(
                    "similarity",
                    Double::class.java,
                    root.get<String>("name"),
                    criteriaBuilder.literal(keyword),
                ),
                0.1, // 유사도 임계값 (0.1 = 10% 이상 유사)
            )
        }
    }

    /**
     * 스토어명에 키워드가 포함된 상품 검색 (대소문자 구분 없음).
     *
     * CQRS 패턴: 비정규화된 store_name 컬럼 사용 (JOIN 불필요, 읽기 최적화).
     */
    fun storeNameContains(keyword: String?): Specification<Product>? {
        if (keyword.isNullOrBlank()) return null
        return Specification { root, _, criteriaBuilder ->
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("storeName")),
                "%${keyword.lowercase()}%",
            )
        }
    }

    /**
     * 스토어명 유사도 검색 (PostgreSQL pg_trgm 확장 활용).
     *
     * similarity() 함수를 사용하여 키워드와 스토어명의 유사도를 계산하고,
     * % 연산자로 임계값(0.3) 이상인 상품을 검색합니다.
     *
     * @param keyword 검색 키워드
     * @return 유사도 기반 검색 Specification
     */
    fun storeNameSimilarTo(keyword: String?): Specification<Product>? {
        if (keyword.isNullOrBlank()) return null
        return Specification { root, _, criteriaBuilder ->
            // PostgreSQL의 % 연산자 사용 (pg_trgm 유사도 검색)
            criteriaBuilder.greaterThanOrEqualTo(
                criteriaBuilder.function(
                    "similarity",
                    Double::class.java,
                    root.get<String>("storeName"),
                    criteriaBuilder.literal(keyword),
                ),
                0.1, // 유사도 임계값 (0.1 = 10% 이상 유사)
            )
        }
    }

    /**
     * 최소 가격 이상인 상품 검색.
     */
    fun priceGreaterThanOrEqual(minPrice: BigDecimal?): Specification<Product>? {
        if (minPrice == null) return null
        return Specification { root, _, criteriaBuilder ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice)
        }
    }

    /**
     * 최대 가격 이하인 상품 검색.
     */
    fun priceLessThanOrEqual(maxPrice: BigDecimal?): Specification<Product>? {
        if (maxPrice == null) return null
        return Specification { root, _, criteriaBuilder ->
            criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice)
        }
    }

    /**
     * 숨김 처리되지 않은 상품만 검색.
     *
     * hiddenAt이 null인 상품만 조회합니다.
     */
    fun notHidden(): Specification<Product> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.isNull(root.get<Any>("hiddenAt"))
        }

    /**
     * 특정 스토어의 상품만 검색.
     */
    fun belongsToStore(storeId: java.util.UUID): Specification<Product> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.equal(root.get<Any>("storeId"), storeId)
        }

    /**
     * 삭제되지 않은 상품만 검색.
     *
     * deletedAt이 null인 상품만 조회합니다.
     */
    fun notDeleted(): Specification<Product> =
        Specification { root, _, criteriaBuilder ->
            criteriaBuilder.isNull(root.get<Any>("deletedAt"))
        }

    /**
     * 여러 Specification을 AND 조건으로 결합.
     */
    fun allOf(vararg specs: Specification<Product>?): Specification<Product> =
        Specification { root, query, criteriaBuilder ->
            val predicates: List<Predicate> =
                specs
                    .filterNotNull()
                    .mapNotNull {
                        it.toPredicate(root, query, criteriaBuilder)
                    }

            if (predicates.isEmpty()) {
                criteriaBuilder.conjunction()
            } else {
                criteriaBuilder.and(*predicates.toTypedArray())
            }
        }
}

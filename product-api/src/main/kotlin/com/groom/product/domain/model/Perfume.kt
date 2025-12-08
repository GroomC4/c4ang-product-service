package com.groom.product.domain.model

import com.groom.product.configuration.jpa.CreatedAndUpdatedAtAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

/**
 * Perfume 엔티티.
 * DDL: p_perfume 테이블
 *
 * 향수 특화 정보를 저장하는 테이블입니다.
 * p_product와 1:1 관계 (논리적 FK, 물리적 제약 없음).
 */
@Entity
@Table(name = "p_perfume")
class Perfume(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val productId: UUID,
    @Column(nullable = false)
    val brand: String,
    @Column(nullable = false)
    val name: String,
    @Column
    val concentration: String? = null,
    @Column(columnDefinition = "jsonb")
    val mainAccords: String? = null,
    @Column(columnDefinition = "jsonb")
    val topNotes: String? = null,
    @Column(columnDefinition = "jsonb")
    val middleNotes: String? = null,
    @Column(columnDefinition = "jsonb")
    val baseNotes: String? = null,
    @Column(columnDefinition = "jsonb")
    val notesScore: String? = null,
    @Column(columnDefinition = "jsonb")
    val seasonScore: String? = null,
    @Column(columnDefinition = "jsonb")
    val dayNightScore: String? = null,
    @Column
    val gender: String? = null,
    @Column(columnDefinition = "jsonb")
    val sizes: String? = null,
    @Column
    val detailUrl: String? = null,
    @Column
    var deletedAt: LocalDateTime? = null,
) : CreatedAndUpdatedAtAuditEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Perfume) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "Perfume(id=$id, brand=$brand, name=$name)"
}

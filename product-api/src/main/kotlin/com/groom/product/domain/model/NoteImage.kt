package com.groom.product.domain.model

import com.groom.product.configuration.jpa.CreatedAndUpdatedAtAuditEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

/**
 * NoteImage 엔티티.
 * DDL: p_note_image 테이블
 *
 * 향수 노트(재료) 이미지를 관리하는 참조 테이블입니다.
 */
@Entity
@Table(name = "p_note_image")
class NoteImage(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    val category: String,
    @Column(nullable = false)
    val noteName: String,
    @Column(nullable = false)
    val imageUrl: String,
) : CreatedAndUpdatedAtAuditEntity() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NoteImage) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "NoteImage(id=$id, category=$category, noteName=$noteName)"
}

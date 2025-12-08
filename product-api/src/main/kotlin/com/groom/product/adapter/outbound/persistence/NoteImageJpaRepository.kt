package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.NoteImage
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

/**
 * NoteImage JPA Repository (Adapter 내부용).
 *
 * 이 인터페이스는 Persistence Adapter 내부에서만 사용됩니다.
 * Domain Layer는 이 인터페이스를 직접 의존하지 않고, Port를 통해 접근합니다.
 */
interface NoteImageJpaRepository : JpaRepository<NoteImage, UUID> {
    fun findByNoteName(noteName: String): Optional<NoteImage>

    fun findAllByNoteNameIn(noteNames: Collection<String>): List<NoteImage>

    fun findByCategory(category: String): List<NoteImage>
}

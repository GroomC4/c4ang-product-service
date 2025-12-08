package com.groom.product.adapter.outbound.persistence

import com.groom.product.domain.model.NoteImage
import com.groom.product.domain.port.LoadNoteImagePort
import org.springframework.stereotype.Component

/**
 * NoteImage 영속성 Adapter.
 *
 * LoadNoteImagePort를 구현하여 Domain Layer와 JPA Repository를 연결합니다.
 */
@Component
class NoteImagePersistenceAdapter(
    private val noteImageJpaRepository: NoteImageJpaRepository,
) : LoadNoteImagePort {
    override fun loadByNoteName(noteName: String): NoteImage? = noteImageJpaRepository.findByNoteName(noteName).orElse(null)

    override fun loadAllByNoteNames(noteNames: Collection<String>): List<NoteImage> =
        noteImageJpaRepository.findAllByNoteNameIn(noteNames)

    override fun loadByCategory(category: String): List<NoteImage> = noteImageJpaRepository.findByCategory(category)

    override fun loadAll(): List<NoteImage> = noteImageJpaRepository.findAll()
}

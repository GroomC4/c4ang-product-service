package com.groom.product.domain.port

import com.groom.product.domain.model.NoteImage

/**
 * NoteImage 조회를 위한 Outbound Port.
 *
 * Domain이 외부 인프라(영속성 계층)에 요구하는 계약입니다.
 */
interface LoadNoteImagePort {
    /**
     * 노트 이름으로 이미지를 조회합니다.
     *
     * @param noteName 노트 이름
     * @return 노트 이미지 또는 null (존재하지 않을 경우)
     */
    fun loadByNoteName(noteName: String): NoteImage?

    /**
     * 여러 노트 이름으로 이미지 목록을 조회합니다.
     *
     * @param noteNames 노트 이름 목록
     * @return 조회된 노트 이미지 목록
     */
    fun loadAllByNoteNames(noteNames: Collection<String>): List<NoteImage>

    /**
     * 카테고리로 노트 이미지 목록을 조회합니다.
     *
     * @param category 카테고리명
     * @return 해당 카테고리의 노트 이미지 목록
     */
    fun loadByCategory(category: String): List<NoteImage>

    /**
     * 모든 노트 이미지를 조회합니다.
     *
     * @return 전체 노트 이미지 목록
     */
    fun loadAll(): List<NoteImage>
}

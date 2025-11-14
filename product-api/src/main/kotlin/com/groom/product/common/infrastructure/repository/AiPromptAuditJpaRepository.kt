package com.groom.product.common.infrastructure.repository

import com.groom.product.common.domain.model.AiPromptAudit
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * AiPromptAudit JPA Repository.
 */
interface AiPromptAuditJpaRepository : JpaRepository<AiPromptAudit, UUID>

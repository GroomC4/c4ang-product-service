package com.groom.product.adapter.outbound.ai

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * AiPromptAudit JPA Repository.
 */
interface AiPromptAuditRepository : JpaRepository<AiPromptAudit, UUID>

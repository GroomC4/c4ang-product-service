package com.groom.product.adapter.outbound.client.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

/**
 * Store Service Internal API 응답 DTO
 *
 * store-service의 Internal API 응답과 동일한 구조
 */
data class StoreInternalDto(
    @JsonProperty("storeId")
    val storeId: String,
    @JsonProperty("ownerUserId")
    val ownerUserId: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("description")
    val description: String?,
    @JsonProperty("status")
    val status: String,
    @JsonProperty("averageRating")
    val averageRating: Double?,
    @JsonProperty("reviewCount")
    val reviewCount: Int?,
    @JsonProperty("launchedAt")
    val launchedAt: LocalDateTime?,
    @JsonProperty("createdAt")
    val createdAt: LocalDateTime,
    @JsonProperty("updatedAt")
    val updatedAt: LocalDateTime,
)

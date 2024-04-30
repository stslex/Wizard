package com.stslex.core.network.clients.match.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchDetailResponse(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("status")
    val status: MatchStatusResponse = MatchStatusResponse.PENDING,
    @SerialName("participants")
    val participants: List<MatchUserResponse>,
    @SerialName("creator_uuid")
    val creatorUuid: String,
    @SerialName("created_at")
    val createdAt: Long,
    @SerialName("updated_at")
    val updatedAt: Long,
    @SerialName("expires_at")
    val expiresAt: Long,
    @SerialName("cover_url")
    val coverUrl: String,
)

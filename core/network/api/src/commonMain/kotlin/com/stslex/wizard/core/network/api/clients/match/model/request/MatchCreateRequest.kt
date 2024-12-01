package com.stslex.wizard.core.network.api.clients.match.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchCreateRequest(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("expires_at")
    val expiresAt: String,
    @SerialName("participants_uuid")
    val participantsUuid: List<String>,
    @SerialName("cover_url")
    val coverUrl: String
)
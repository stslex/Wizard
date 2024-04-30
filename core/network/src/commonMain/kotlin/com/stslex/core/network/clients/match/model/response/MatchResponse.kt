package com.stslex.core.network.clients.match.model.response

import com.stslex.core.core.paging.PagingCoreItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchResponse(
    @SerialName("uuid")
    override val uuid: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("status")
    val status: MatchStatusResponse,
    @SerialName("creator_uuid")
    val creatorUuid: String,
    @SerialName("participants")
    val participants: List<MatchUserResponse>,
    @SerialName("expires_at")
    val expiresAt: Long,
    @SerialName("cover_url")
    val coverUrl: String,
) : PagingCoreItem

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
    @SerialName("participants")
    val participants: List<MatchUserResponse>,
    @SerialName("is_creator")
    val isCreator: Boolean,
    @SerialName("expires_at")
    val expiresAt: Long,
) : PagingCoreItem

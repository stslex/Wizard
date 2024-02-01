package com.stslex.core.network.clients.profile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSearchResponse(
    @SerialName("result")
    val result: List<UserSearchResultResponse>,
)

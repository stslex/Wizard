package com.stslex.core.network.clients.profile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFollowerResultResponse(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("username")
    val username: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
)
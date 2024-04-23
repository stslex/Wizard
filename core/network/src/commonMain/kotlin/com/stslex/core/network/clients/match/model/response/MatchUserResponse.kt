package com.stslex.core.network.clients.match.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchUserResponse(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("avatar")
    val avatar: String,
    @SerialName("username")
    val username: String,
    @SerialName("is_creator")
    val isCreator: Boolean,
    @SerialName("is_accepted")
    val isAccepted: Boolean,
)
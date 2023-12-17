package com.stslex.core.network.clients.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginOkResponse(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("username")
    val username: String,
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String
)
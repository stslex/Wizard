package com.stslex.core.network.clients.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    @SerialName("login")
    val login: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)
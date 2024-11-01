package com.stslex.wizard.core.network.clients.profile.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSearchResponse(
    @SerialName("result")
    val result: List<UserResponse>,
)
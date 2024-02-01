package com.stslex.core.network.clients.profile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFavouriteResponse(
    @SerialName("result")
    val result: List<UserFavouriteResultResponse>,
)


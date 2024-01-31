package com.stslex.core.network.clients.profile.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFavouriteResultResponse(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("title")
    val title: String,
)
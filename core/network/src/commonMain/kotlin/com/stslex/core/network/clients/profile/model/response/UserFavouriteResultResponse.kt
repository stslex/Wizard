package com.stslex.core.network.clients.profile.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserFavouriteResultResponse(
    @SerialName("uuid")
    val uuid: String,
    @SerialName("title")
    val title: String,
    @SerialName("is_favourite")
    val isFavourite: Boolean,
)
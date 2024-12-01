package com.stslex.wizard.core.network.api.clients.profile.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddLikeRequest(
    @SerialName("favourite_uuid")
    val filmUuid: String,
    @SerialName("title")
    val title: String
)
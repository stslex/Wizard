package com.stslex.core.network.clients.profile.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooleanResponse(
    @SerialName("result")
    val result: Boolean
)

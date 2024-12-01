package com.stslex.wizard.core.network.api.clients.match.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MatchStatusResponse {
    @SerialName("pending")
    PENDING,

    @SerialName("active")
    ACTIVE,

    @SerialName("expired")
    EXPIRED,

    @SerialName("completed")
    COMPLETED,

    @SerialName("canceled")
    CANCELED,
}
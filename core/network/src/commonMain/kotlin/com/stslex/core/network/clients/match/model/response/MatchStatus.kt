package com.stslex.core.network.clients.match.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MatchStatus {
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
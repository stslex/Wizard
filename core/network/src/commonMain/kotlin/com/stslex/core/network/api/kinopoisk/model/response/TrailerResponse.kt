package com.stslex.core.network.api.kinopoisk.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerResponse(
    @SerialName("total")
    val total: Int?,
    @SerialName("items")
    val items: List<TrailerItemResponse>?
)


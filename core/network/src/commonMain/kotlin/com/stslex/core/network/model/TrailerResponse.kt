package com.stslex.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerResponse(
    @SerialName("total")
    val total: Int,
    @SerialName("items")
    val items: List<TrailerItemResponse>
)


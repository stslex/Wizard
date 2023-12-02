package com.stslex.core.network.api.kinopoisk.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrailerItemResponse(
    @SerialName("url")
    val url: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("site")
    val site: String?
)
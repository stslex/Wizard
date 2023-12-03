package com.stslex.core.network.api.kinopoisk.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsRequest(
    @SerialName("page")
    val page: Int,
)

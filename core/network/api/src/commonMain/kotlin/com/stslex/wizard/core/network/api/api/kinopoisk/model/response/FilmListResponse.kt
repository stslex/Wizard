package com.stslex.wizard.core.network.api.api.kinopoisk.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmListResponse(
    @SerialName("total")
    val total: Int?,
    @SerialName("totalPages")
    val totalPages: Int?,
    @SerialName("items")
    val items: List<FilmItemResponse>?
)
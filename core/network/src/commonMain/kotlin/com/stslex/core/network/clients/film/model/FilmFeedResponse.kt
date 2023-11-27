package com.stslex.core.network.clients.film.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmFeedResponse(
    @SerialName("results")
    val results: List<FilmResponse>,
    @SerialName("has_next")
    val hasNext: Boolean
)

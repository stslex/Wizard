package com.stslex.core.network.clients.film.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmResponse(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("poster")
    val poster: String,
    @SerialName("rating")
    val rating: String,
    @SerialName("duration")
    val duration: String,
    @SerialName("genres")
    val genres: List<String>,
    @SerialName("actors")
    val actors: List<String>,
    @SerialName("director")
    val director: String,
    @SerialName("country")
    val country: String,
    @SerialName("year")
    val year: String,
    @SerialName("age")
    val age: String,
    @SerialName("type")
    val type: String,
    @SerialName("trailer")
    val trailer: String,
    @SerialName("isFavorite")
    val isFavorite: Boolean
)

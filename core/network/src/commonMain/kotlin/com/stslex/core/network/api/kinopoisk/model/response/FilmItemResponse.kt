package com.stslex.core.network.api.kinopoisk.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmItemResponse(
    @SerialName("kinopoiskId")
    val kinopoiskId: Int,
    @SerialName("imdbId")
    val imdbId: String?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("nameEn")
    val nameEn: String?,
    @SerialName("nameOriginal")
    val nameOriginal: String?,
    @SerialName("countries")
    val countries: List<CountryResponse>?,
    @SerialName("genres")
    val genres: List<GenreResponse>?,
    @SerialName("ratingKinopoisk")
    val ratingKinopoisk: Double?,
    @SerialName("ratingImdb")
    val ratingImdb: Double?,
    @SerialName("year")
    val year: Int?,
    @SerialName("type")
    val type: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String?
)
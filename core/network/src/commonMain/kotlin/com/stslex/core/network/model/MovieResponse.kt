package com.stslex.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerialName("kinopoiskId")
    val kinopoiskId: Int,
    @SerialName("kinopoiskHDId")
    val kinopoiskHDId: String?,
    @SerialName("imdbId")
    val imdbId: String?,
    @SerialName("nameRu")
    val nameRu: String?,
    @SerialName("nameEn")
    val nameEn: String?,
    @SerialName("nameOriginal")
    val nameOriginal: String?,
    @SerialName("posterUrl")
    val posterUrl: String?,
    @SerialName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerialName("coverUrl")
    val coverUrl: String?,
    @SerialName("logoUrl")
    val logoUrl: String?,
    @SerialName("reviewsCount")
    val reviewsCount: Int?,
    @SerialName("ratingGoodReview")
    val ratingGoodReview: Double?,
    @SerialName("ratingGoodReviewVoteCount")
    val ratingGoodReviewVoteCount: Int?,
    @SerialName("ratingKinopoisk")
    val ratingKinopoisk: Double?,
    @SerialName("ratingKinopoiskVoteCount")
    val ratingKinopoiskVoteCount: Int?,
    @SerialName("ratingImdb")
    val ratingImdb: Double?,
    @SerialName("ratingImdbVoteCount")
    val ratingImdbVoteCount: Int?,
    @SerialName("ratingFilmCritics")
    val ratingFilmCritics: Double?,
    @SerialName("ratingFilmCriticsVoteCount")
    val ratingFilmCriticsVoteCount: Int?,
    @SerialName("ratingAwait")
    val ratingAwait: Double?,
    @SerialName("ratingAwaitCount")
    val ratingAwaitCount: Int?,
    @SerialName("ratingRfCritics")
    val ratingRfCritics: Double?,
    @SerialName("ratingRfCriticsVoteCount")
    val ratingRfCriticsVoteCount: Int?,
    @SerialName("webUrl")
    val webUrl: String?,
    @SerialName("year")
    val year: Int?,
    @SerialName("filmLength")
    val filmLength: Int?,
    @SerialName("slogan")
    val slogan: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("shortDescription")
    val shortDescription: String?,
    @SerialName("editorAnnotation")
    val editorAnnotation: String?,
    @SerialName("isTicketsAvailable")
    val isTicketsAvailable: Boolean?,
    @SerialName("productionStatus")
    val productionStatus: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("ratingMpaa")
    val ratingMpaa: String?,
    @SerialName("ratingAgeLimits")
    val ratingAgeLimits: String?,
    @SerialName("hasImax")
    val hasImax: Boolean?,
    @SerialName("has3D")
    val has3D: Boolean?,
    @SerialName("lastSync")
    val lastSync: String?,
    @SerialName("countries")
    val countries: List<CountryResponse>?,
    @SerialName("genres")
    val genres: List<GenreResponse>?,
    @SerialName("startYear")
    val startYear: Int?,
    @SerialName("endYear")
    val endYear: Int?,
    @SerialName("serial")
    val serial: Boolean?,
    @SerialName("shortFilm")
    val shortFilm: Boolean?,
    @SerialName("completed")
    val completed: Boolean?
)

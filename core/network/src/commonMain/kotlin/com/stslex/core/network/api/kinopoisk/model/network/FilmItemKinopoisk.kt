package com.stslex.core.network.api.kinopoisk.model.network

data class FilmItemKinopoisk(
    val kinopoiskId: Int,
    val imdbId: String,
    val nameRu: String,
    val nameEn: String,
    val nameOriginal: String,
    val countries: List<String>,
    val genres: List<String>,
    val ratingKinopoisk: Double?,
    val ratingImdb: Double?,
    val year: Int?,
    val type: FilmTypeNetwork,
    val posterUrl: String,
    val posterUrlPreview: String
)
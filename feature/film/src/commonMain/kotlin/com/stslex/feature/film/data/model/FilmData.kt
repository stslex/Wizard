package com.stslex.feature.film.data.model

data class FilmData(
    val id: String,
    val title: String,
    val description: String,
    val poster: String,
    val rating: String,
    val reviews: String,
    val duration: String,
    val genres: List<String>,
    val actors: String,
    val director: String,
    val country: String,
    val year: String,
    val age: String,
    val type: String,
    val trailer: String,
    val isFavorite: Boolean
)

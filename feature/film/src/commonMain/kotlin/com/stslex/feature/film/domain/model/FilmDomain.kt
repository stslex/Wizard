package com.stslex.feature.film.domain.model

data class FilmDomain(
    val id: String,
    val title: String,
    val description: String,
    val poster: String,
    val rating: String,
    val duration: String,
    val genres: List<String>,
    val actors: List<String>,
    val director: String,
    val countries: List<String>,
    val year: String,
    val age: String,
    val type: String,
    val trailer: String,
    val isFavorite: Boolean
)

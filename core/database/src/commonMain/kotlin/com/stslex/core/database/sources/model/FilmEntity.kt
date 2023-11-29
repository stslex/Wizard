package com.stslex.core.database.sources.model

data class FilmEntity(
    val id: String,
    val title: String,
    val description: String,
    val poster: String,
    val rating: String,
    val duration: String,
    val genres: List<String>,
    val actors: List<String>,
    val director: String,
    val country: String,
    val year: String,
    val age: String,
    val type: String,
    val trailer: String,
)

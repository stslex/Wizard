package com.stslex.wizard.core.network.api.clients.film.model

data class FilmItemNetwork(
    val id: String,
    val title: String, // TODO different languages
    val poster: String,
    val rating: String,
    val genres: List<String>,
    val countries: List<String>,
    val year: String,
    val type: String, // TODO different languages
)

package com.stslex.wizard.core.network.clients.film.model

data class MovieNetwork(
    val id: String,
    val title: String,
    val description: String,
    val poster: String,
    val rating: String,
    val duration: String,
    val genres: List<String>,
    val countries: List<String>,
    val year: String,
    val age: String,
    val type: String,
)

package com.stslex.core.network.clients.film.model

data class FilmListNetwork(
    val results: List<FilmItemNetwork>,
    val hasNext: Boolean
)

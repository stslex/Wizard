package com.stslex.core.network.api.kinopoisk.model.network

data class FilmListNetwork(
    val total: Int,
    val totalPages: Int,
    val items: List<FilmItemNetwork>
)
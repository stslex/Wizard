package com.stslex.wizard.core.network.api.kinopoisk.model.network

data class FilmListKinopoisk(
    val total: Int,
    val totalPages: Int,
    val items: List<FilmItemKinopoisk>
)
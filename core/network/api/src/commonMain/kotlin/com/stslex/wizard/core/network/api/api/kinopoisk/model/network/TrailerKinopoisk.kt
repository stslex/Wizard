package com.stslex.wizard.core.network.api.api.kinopoisk.model.network

data class TrailerKinopoisk(
    val total: Int,
    val items: List<TrailerItemKinopoisk>
)
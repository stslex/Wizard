package com.stslex.core.network.api.kinopoisk.model.network

import com.stslex.core.network.clients.film.model.TrailerSiteType

data class TrailerItemKinopoisk(
    val url: String,
    val name: String,
    val site: TrailerSiteType
)
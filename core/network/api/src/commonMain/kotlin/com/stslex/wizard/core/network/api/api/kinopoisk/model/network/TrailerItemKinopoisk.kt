package com.stslex.wizard.core.network.api.api.kinopoisk.model.network

import com.stslex.wizard.core.network.api.clients.film.model.TrailerSiteType

data class TrailerItemKinopoisk(
    val url: String,
    val name: String,
    val site: TrailerSiteType
)
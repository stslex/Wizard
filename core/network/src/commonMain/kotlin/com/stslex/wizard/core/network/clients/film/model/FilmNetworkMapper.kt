package com.stslex.wizard.core.network.clients.film.model

import com.stslex.wizard.core.network.api.kinopoisk.model.network.FilmItemKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.FilmListKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.MovieKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.TrailerItemKinopoisk
import com.stslex.wizard.core.network.api.kinopoisk.model.network.TrailerKinopoisk

fun FilmListKinopoisk.toNetwork() = FilmListNetwork(
    results = items.map { it.toNetwork() },
    hasNext = total > totalPages
)

fun FilmItemKinopoisk.toNetwork() = FilmItemNetwork(
    id = kinopoiskId.toString(),
    title = nameRu,
    poster = posterUrlPreview,
    rating = (ratingImdb?.toString() ?: ratingKinopoisk?.toString()).orEmpty(),
    genres = genres,
    countries = countries,
    year = year?.toString().orEmpty(),
    type = type.value.lowercase(),
)

fun TrailerKinopoisk.toNetwork(): List<FilmTrailerNetwork> = this.items.map { it.toNetwork() }

fun TrailerItemKinopoisk.toNetwork() =
    com.stslex.wizard.core.network.clients.film.model.FilmTrailerNetwork(
        url = url,
        name = name,
        site = site,
    )

fun MovieKinopoisk.toNetwork() =
    MovieNetwork(
        id = kinopoiskId.toString(),
        title = nameRu,
        description = description.orEmpty(),
        poster = posterUrl,
        rating = (ratingImdb?.toString() ?: ratingKinopoisk?.toString()).orEmpty(),
        duration = filmLength?.toString().orEmpty(),
        genres = genres,
        countries = countries,
        year = year?.toString().orEmpty(),
        age = ratingAgeLimits.orEmpty(),
        type = type.orEmpty().lowercase(),
    )
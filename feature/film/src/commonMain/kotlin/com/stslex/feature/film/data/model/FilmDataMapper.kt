package com.stslex.feature.film.data.model

import com.stslex.core.database.sources.model.FilmEntity
import com.stslex.core.network.clients.film.model.FilmTrailerNetwork
import com.stslex.core.network.clients.film.model.MovieNetwork
import com.stslex.core.network.clients.film.model.TrailerSiteType

fun MovieNetwork.toData(
    isFavourite: Boolean,
    trailer: String,
) = FilmData(
    id = id,
    title = title,
    description = description,
    poster = poster,
    rating = rating,
    duration = duration,
    actors = emptyList(), //  TODO add into details model
    director = "", // TODO add into details model
    countries = countries,
    year = year,
    age = age,
    type = type,
    trailer = trailer,
    isFavorite = isFavourite,
    genres = genres,
)

fun FilmData.toEntity(): FilmEntity = FilmEntity(
    id = id,
    title = title,
    description = description,
    poster = poster,
    rating = rating,
    duration = duration,
    genres = genres,
    actors = actors,
    director = director,
    countries = countries,
    year = year,
    age = age,
    type = type,
    trailer = trailer,
)

fun List<FilmTrailerNetwork>.getTrailer(): String = this
    .filter { it.url.isNotBlank() }
    .run {
        firstOrNull { it.site == TrailerSiteType.YOUTUBE }
            ?: firstOrNull { it.site == TrailerSiteType.YANDEX_DISK }
            ?: firstOrNull { it.site == TrailerSiteType.KINOPOISK_WIDGET }
            ?: firstOrNull()
    }
    ?.url
    .orEmpty()
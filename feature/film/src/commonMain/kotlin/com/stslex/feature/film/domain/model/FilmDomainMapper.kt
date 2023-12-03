package com.stslex.feature.film.domain.model

import com.stslex.feature.film.data.model.FilmData

fun FilmData.toDomain() = FilmDomain(
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
    isFavorite = isFavorite
)

fun FilmDomain.toData(): FilmData = FilmData(
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
    isFavorite = isFavorite
)
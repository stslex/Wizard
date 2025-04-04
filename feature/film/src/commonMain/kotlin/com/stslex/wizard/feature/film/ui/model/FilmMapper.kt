package com.stslex.wizard.feature.film.ui.model

import com.stslex.wizard.feature.film.domain.model.FilmDomain
import kotlinx.collections.immutable.toImmutableList

fun FilmDomain.toUi() = Film(
    id = id,
    title = title,
    description = description,
    poster = poster,
    rating = rating,
    duration = duration,
    genres = genres.toImmutableList(),
    actors = actors.toImmutableList(),
    director = director,
    countries = countries.toImmutableList(),
    year = year,
    age = age,
    type = type,
    trailer = trailer,
    isFavorite = isFavorite
)

fun Film.toDomain(): FilmDomain = FilmDomain(
    id = id,
    title = title,
    description = description,
    poster = poster,
    rating = rating,
    duration = duration,
    genres = genres.toImmutableList(),
    actors = actors.toImmutableList(),
    director = director,
    countries = countries,
    year = year,
    age = age,
    type = type,
    trailer = trailer,
    isFavorite = isFavorite
)
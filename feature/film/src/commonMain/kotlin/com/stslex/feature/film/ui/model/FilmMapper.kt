package com.stslex.feature.film.ui.model

import com.stslex.feature.film.domain.model.FilmDomain
import kotlinx.collections.immutable.toImmutableList

fun FilmDomain.toUi() = Film(
    id = id,
    title = title,
    description = description,
    poster = poster,
    rating = rating,
    reviews = reviews,
    duration = duration,
    genres = genres.toImmutableList(),
    actors = actors,
    director = director,
    country = country,
    year = year,
    age = age,
    type = type,
    trailer = trailer,
    isFavorite = isFavorite
)
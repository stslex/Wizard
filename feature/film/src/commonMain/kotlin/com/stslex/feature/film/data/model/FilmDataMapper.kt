package com.stslex.feature.film.data.model

import com.stslex.core.network.clients.film.model.FilmResponse

fun FilmResponse.toData() = FilmData(
    id = id,
    title = title,
    description = description,
    poster = poster,
    rating = rating,
    duration = duration,
    genres = genres,
    actors = actors,
    director = director,
    country = country,
    year = year,
    age = age,
    type = type,
    trailer = trailer,
    isFavorite = isFavorite
)
package com.stslex.feature.film_feed.data.model

import com.stslex.core.network.clients.film.model.FilmFeedResponse
import com.stslex.core.network.clients.film.model.FilmResponse

fun FilmFeedResponse.toData() = FeedDataModel(
    films = results.map { it.toData() },
    hasNextPage = hasNext,
)

fun FilmResponse.toData() = FilmDataModel(
    id = id,
    title = title,
    description = description,
    imageUrl = poster,
    rate = rating,
    genres = genres,
)
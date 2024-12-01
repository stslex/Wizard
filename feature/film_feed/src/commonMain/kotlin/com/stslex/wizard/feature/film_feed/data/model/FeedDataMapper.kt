package com.stslex.wizard.feature.film_feed.data.model

import com.stslex.wizard.core.network.api.clients.film.model.FilmItemNetwork
import com.stslex.wizard.core.network.api.clients.film.model.FilmListNetwork

fun FilmListNetwork.toData() = FeedDataModel(
    films = results.map { it.toData() },
    hasNextPage = hasNext,
)

fun FilmItemNetwork.toData() = FilmDataModel(
    id = id,
    title = title,
    imageUrl = poster,
    rate = rating,
    genres = genres,
)
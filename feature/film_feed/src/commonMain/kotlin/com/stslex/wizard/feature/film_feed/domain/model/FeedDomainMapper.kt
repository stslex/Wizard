package com.stslex.wizard.feature.film_feed.domain.model

import com.stslex.feature.film_feed.data.model.FeedDataModel
import com.stslex.feature.film_feed.data.model.FilmDataModel

fun FeedDataModel.toDomain() = FeedDomainModel(
    films = films.map { it.toDomain() },
    hasNextPage = hasNextPage,
)

fun FilmDataModel.toDomain() = FilmDomainModel(
    id = id,
    title = title,
    imageUrl = imageUrl,
    rate = rate,
    genres = genres,
)
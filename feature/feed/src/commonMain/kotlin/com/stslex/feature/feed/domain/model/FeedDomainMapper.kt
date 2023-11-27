package com.stslex.feature.feed.domain.model

import com.stslex.feature.feed.data.model.FeedDataModel
import com.stslex.feature.feed.data.model.FilmDataModel

fun FeedDataModel.toDomain() = FeedDomainModel(
    films = films.map { it.toDomain() },
    hasNextPage = hasNextPage,
)

fun FilmDataModel.toDomain() = FilmDomainModel(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    rate = rate,
    genres = genres,
)
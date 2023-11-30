package com.stslex.feature.film_feed.ui.model

import com.stslex.feature.film_feed.domain.model.FilmDomainModel
import kotlinx.collections.immutable.toImmutableList

fun List<FilmDomainModel>.toUI() = map { it.toUi() }.toImmutableList()

fun FilmDomainModel.toUi() = FilmModel(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    rate = rate,
    genres = genres.toImmutableList()
)
package com.stslex.feature.feed.ui.model

import com.stslex.feature.feed.domain.model.FilmDomainModel
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
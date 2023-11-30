package com.stslex.feature.film_feed.domain.model

data class FeedDomainModel(
    val films: List<FilmDomainModel>,
    val hasNextPage: Boolean,
)

package com.stslex.feature.feed.domain.model

data class FeedDomainModel(
    val films: List<FilmDomainModel>,
    val hasNextPage: Boolean,
)

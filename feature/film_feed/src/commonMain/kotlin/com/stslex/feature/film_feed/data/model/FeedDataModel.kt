package com.stslex.feature.film_feed.data.model

data class FeedDataModel(
    val films: List<FilmDataModel>,
    val hasNextPage: Boolean,
)
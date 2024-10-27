package com.stslex.wizard.feature.film_feed.data.model

data class FeedDataModel(
    val films: List<FilmDataModel>,
    val hasNextPage: Boolean,
)
package com.stslex.feature.feed.data.model

data class FeedDataModel(
    val films: List<FilmDataModel>,
    val hasNextPage: Boolean,
)
package com.stslex.feature.match_feed.data.model

data class MatchFilmsPagingData(
    val films: List<FilmData>,
    val hasNext: Boolean,
)

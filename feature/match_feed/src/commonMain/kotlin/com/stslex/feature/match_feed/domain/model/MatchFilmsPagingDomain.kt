package com.stslex.feature.match_feed.domain.model

data class MatchFilmsPagingDomain(
    val films: List<FilmDomain>,
    val hasNext: Boolean,
)

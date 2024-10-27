package com.stslex.wizard.feature.match_feed.domain.model

data class FilmDomain(
    val uuid: String,
    val title: String,
    val description: String,
    val poster: String,
    val rate: String,
    val genres: List<String>,
)

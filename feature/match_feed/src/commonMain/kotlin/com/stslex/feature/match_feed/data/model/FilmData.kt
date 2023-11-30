package com.stslex.feature.match_feed.data.model

data class FilmData(
    val uuid: String,
    val title: String,
    val description: String,
    val poster: String,
    val rate: String,
    val genres: List<String>,
)
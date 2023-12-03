package com.stslex.feature.film_feed.data.model

data class FilmDataModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    val rate: String,
    val genres: List<String>,
)

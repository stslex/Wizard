package com.stslex.feature.feed.data.model

data class FilmDataModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rate: String,
    val genres: List<String>,
)

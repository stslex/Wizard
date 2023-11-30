package com.stslex.feature.film_feed.domain.model

data class FilmDomainModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rate: String,
    val genres: List<String>,
)

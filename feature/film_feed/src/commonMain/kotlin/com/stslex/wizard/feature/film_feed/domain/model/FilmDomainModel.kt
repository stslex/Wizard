package com.stslex.wizard.feature.film_feed.domain.model

data class FilmDomainModel(
    val id: String,
    val title: String,
    val imageUrl: String,
    val rate: String,
    val genres: List<String>,
)

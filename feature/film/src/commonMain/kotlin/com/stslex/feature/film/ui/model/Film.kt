package com.stslex.feature.film.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

@Stable
data class Film(
    val id: String,
    val title: String,
    val description: String,
    val poster: String,
    val rating: String,
    val reviews: String,
    val duration: String,
    val genres: ImmutableList<String>,
    val actors: String,
    val director: String,
    val country: String,
    val year: String,
    val age: String,
    val type: String,
    val trailer: String,
    val isFavorite: Boolean
)

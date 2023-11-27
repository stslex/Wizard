package com.stslex.feature.feed.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

@Stable
data class FilmModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val rate: String,
    val genres: ImmutableList<String>,
)
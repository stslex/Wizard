package com.stslex.feature.feed.ui.model

import androidx.compose.runtime.Stable

@Stable
data class FilmModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
)
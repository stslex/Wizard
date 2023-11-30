package com.stslex.feature.match_feed.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

@Stable
data class FilmUi(
    val uuid: String,
    val title: String,
    val description: String,
    val poster: String,
    val rate: String,
    val genres: ImmutableList<String>,
)

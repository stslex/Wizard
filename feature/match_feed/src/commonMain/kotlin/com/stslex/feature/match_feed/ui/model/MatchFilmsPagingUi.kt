package com.stslex.feature.match_feed.ui.model

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.ImmutableList

@Stable
data class MatchFilmsPagingUi(
    val films: ImmutableList<FilmUi>,
    val hasNext: Boolean,
)

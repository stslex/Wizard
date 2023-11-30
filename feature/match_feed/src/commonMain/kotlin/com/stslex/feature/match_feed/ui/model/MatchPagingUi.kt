package com.stslex.feature.match_feed.ui.model

import kotlinx.collections.immutable.ImmutableList
import androidx.compose.runtime.Stable

@Stable
data class MatchPagingUi(
    val matches: ImmutableList<MatchUi>,
    val hasNext: Boolean,
)

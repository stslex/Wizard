package com.stslex.feature.match_feed.ui.model

import androidx.compose.runtime.Stable

@Stable
data class MatchUi(
    val uuid: String,
    val title: String,
    val participant: MatchParticipantUi,
    val count: Int,
)

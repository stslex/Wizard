package com.stslex.wizard.feature.match_feed.ui.model

import androidx.compose.runtime.Stable

@Stable
data class MatchParticipantUi(
    val uuid: String,
    val name: String,
    val avatar: String,
)
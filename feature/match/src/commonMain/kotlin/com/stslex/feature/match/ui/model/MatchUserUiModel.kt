package com.stslex.feature.match.ui.model

import androidx.compose.runtime.Stable

@Stable
data class MatchUserUiModel(
    val uuid: String,
    val avatar: String,
    val username: String,
    val isCreator: Boolean,
    val isAccepted: Boolean,
)
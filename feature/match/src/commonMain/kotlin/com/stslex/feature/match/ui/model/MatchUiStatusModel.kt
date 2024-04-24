package com.stslex.feature.match.ui.model

import androidx.compose.runtime.Stable

@Stable
enum class MatchUiStatusModel {
    PENDING,
    ACTIVE,
    EXPIRED,
    COMPLETED,
    CANCELED,
}
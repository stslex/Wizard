package com.stslex.feature.match.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.AppError

@Stable
sealed interface MatchScreenState {

    @Stable
    data object Shimmer : MatchScreenState

    @Stable
    data object Empty : MatchScreenState

    @Stable
    data class Error(
        val error: AppError
    ) : MatchScreenState

    @Stable
    sealed interface Content : MatchScreenState {

        @Stable
        data object Data : Content

        @Stable
        data object Append : Content

        @Stable
        data object Refresh : Content
    }
}
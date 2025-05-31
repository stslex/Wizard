package com.stslex.wizard.feature.match.ui.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.kit.base.AppError

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
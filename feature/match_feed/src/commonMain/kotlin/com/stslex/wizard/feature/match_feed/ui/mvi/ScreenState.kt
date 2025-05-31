package com.stslex.wizard.feature.match_feed.ui.mvi

import androidx.compose.runtime.Stable

@Stable
sealed interface ScreenState {

    @Stable
    sealed interface Content : ScreenState {

        @Stable
        data object AppendLoading : Content

        @Stable
        data object Success : Content
    }

    @Stable
    data object Loading : ScreenState

    @Stable
    data class Error(
        val message: String
    ) : ScreenState
}
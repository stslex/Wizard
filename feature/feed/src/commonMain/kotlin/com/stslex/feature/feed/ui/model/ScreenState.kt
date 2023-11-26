package com.stslex.feature.feed.ui.model

import androidx.compose.runtime.Stable

sealed interface ScreenState {

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


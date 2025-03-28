package com.stslex.wizard.feature.film_feed.ui.model

import androidx.compose.runtime.Stable

@Stable
sealed interface ScreenState {

    @Stable
    sealed interface Content : ScreenState {

        @Stable
        data object Shimmer : Content

        @Stable
        data object AppendLoading : Content

        @Stable
        data object Success : Content
    }

    @Stable
    data class Error(val message: String) : ScreenState
}


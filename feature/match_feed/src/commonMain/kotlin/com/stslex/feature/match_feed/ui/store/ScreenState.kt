package com.stslex.feature.match_feed.ui.store

import androidx.compose.runtime.Stable

sealed interface ScreenState {

    sealed interface Content : ScreenState {

        data object AppendLoading : Content
        data object Success : Content
    }

    data object Loading : ScreenState

    @Stable
    data class Error(
        val message: String
    ) : ScreenState
}
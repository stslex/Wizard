package com.stslex.feature.favourite.ui.store

import androidx.compose.runtime.Stable

@Stable
sealed interface FavouriteScreenState {

    @Stable
    sealed interface Content : FavouriteScreenState {

        @Stable
        data object Data : Content

        @Stable
        data object Empty : Content
    }

    @Stable
    data object Shimmer : FavouriteScreenState

    @Stable
    data class Error(val error: Throwable) : FavouriteScreenState
}
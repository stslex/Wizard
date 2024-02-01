package com.stslex.feature.favourite.ui.store

import androidx.compose.runtime.Stable

@Stable
sealed interface FavouriteScreenState {

    @Stable
    sealed interface Content : FavouriteScreenState {

        @Stable
        data object NotLoading : Content

        @Stable
        data object Loading : Content
    }

    @Stable
    data object Shimmer : FavouriteScreenState

    @Stable
    data object Empty : FavouriteScreenState

    @Stable
    data class Error(val error: Throwable) : FavouriteScreenState
}
package com.stslex.wizard.feature.favourite.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.AppError
import com.stslex.core.ui.pager.states.PagerLoadState

@Stable
sealed interface FavouriteScreenState {

    @Stable
    sealed interface Content : FavouriteScreenState {

        @Stable
        data object Data : Content

        @Stable
        data object Loading : Content

        @Stable
        data object Refresh : Content
    }

    @Stable
    data object Shimmer : FavouriteScreenState

    @Stable
    data object Empty : Content

    @Stable
    data class Error(val error: AppError) : FavouriteScreenState
}

fun PagerLoadState.toUi() = when (this) {
    PagerLoadState.Data -> FavouriteScreenState.Content.Data
    PagerLoadState.Empty -> FavouriteScreenState.Empty
    is PagerLoadState.Error -> FavouriteScreenState.Error(error)
    PagerLoadState.Initial -> FavouriteScreenState.Shimmer
    PagerLoadState.Loading -> FavouriteScreenState.Content.Loading
    PagerLoadState.Refresh -> FavouriteScreenState.Content.Refresh
    PagerLoadState.Retry -> FavouriteScreenState.Shimmer
}
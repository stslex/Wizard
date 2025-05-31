package com.stslex.wizard.feature.follower.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.kit.base.AppError
import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState

@Stable
sealed interface FollowerScreenState {

    @Stable
    sealed interface Content : FollowerScreenState {

        @Stable
        data object Data : Content

        @Stable
        data object Loading : Content

        @Stable
        data object Refresh : Content
    }

    @Stable
    data object Shimmer : FollowerScreenState

    @Stable
    data object Empty : FollowerScreenState

    @Stable
    data class Error(val error: AppError) : FollowerScreenState
}

fun PagerLoadState.toUi(): FollowerScreenState = when (this) {
    is PagerLoadState.Loading -> FollowerScreenState.Content.Loading
    is PagerLoadState.Error -> FollowerScreenState.Error(error)
    is PagerLoadState.Initial -> FollowerScreenState.Shimmer
    is PagerLoadState.Empty -> FollowerScreenState.Empty
    PagerLoadState.Data -> FollowerScreenState.Content.Data
    PagerLoadState.Refresh -> FollowerScreenState.Content.Refresh
    PagerLoadState.Retry -> FollowerScreenState.Shimmer
}
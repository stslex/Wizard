package com.stslex.wizard.feature.follower.mvi

import com.stslex.wizard.core.ui.kit.pager.states.PagerLoadState

object PagerLoadStateMapper {

    fun PagerLoadState.toUi(): FollowerScreenState = when (this) {
        is PagerLoadState.Loading -> FollowerScreenState.Content.Loading
        is PagerLoadState.Error -> FollowerScreenState.Error(error)
        is PagerLoadState.Initial -> FollowerScreenState.Shimmer
        is PagerLoadState.Empty -> FollowerScreenState.Empty
        PagerLoadState.Data -> FollowerScreenState.Content.Data
        PagerLoadState.Refresh -> FollowerScreenState.Content.Refresh
        PagerLoadState.Retry -> FollowerScreenState.Shimmer
    }
}
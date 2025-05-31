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

package com.stslex.feature.follower.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store
import com.stslex.feature.follower.navigation.FollowerScreenArgs
import com.stslex.feature.follower.ui.model.FollowerModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface FollowerStoreComponent : Store {

    @Stable
    data class State(
        val uuid: String,
        val page: Int,
        val type: FollowerScreenArgs,
        val data: ImmutableList<FollowerModel>,
        val screen: FollowerScreenState
    ) : Store.State {

        companion object {

            const val DEFAULT_PAGE = -1

            val INITIAL = State(
                uuid = "",
                page = DEFAULT_PAGE,
                type = FollowerScreenArgs.Follower(""),
                data = emptyList<FollowerModel>().toImmutableList(),
                screen = FollowerScreenState.Shimmer
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        data class Init(
            val args: FollowerScreenArgs
        ) : Action

        @Stable
        data object LoadMore : Action
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ErrorSnackBar(val message: String) : Event
    }

    sealed interface Navigation : Store.Navigation
}

@Stable
sealed interface FollowerScreenState {

    @Stable
    sealed interface Content : FollowerScreenState {

        @Stable
        data object NotLoading : Content

        @Stable
        data object Loading : Content
    }

    @Stable
    data object Shimmer : FollowerScreenState

    @Stable
    data object Empty : FollowerScreenState

    @Stable
    data class Error(val error: Throwable) : FollowerScreenState
}
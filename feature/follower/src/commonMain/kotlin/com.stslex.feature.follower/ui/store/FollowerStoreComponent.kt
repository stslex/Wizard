package com.stslex.feature.follower.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.mvi.Store
import com.stslex.feature.follower.ui.FollowerScreenArgs
import com.stslex.feature.follower.ui.model.FollowerModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

interface FollowerStoreComponent : Store {

    @Stable
    data class State(
        val uuid: String,
        val data: ImmutableList<FollowerModel>,
        val screen: FollowerScreenState
    ) : Store.State {

        companion object {

            val INITIAL = State(
                uuid = "",
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

        data object NotLoading : Content

        data object Loading : Content
    }

    @Stable
    data object Shimmer : FollowerScreenState

    @Stable
    data class Error(val error: Throwable) : FollowerScreenState
}
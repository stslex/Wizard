package com.stslex.feature.follower.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingConfig
import com.stslex.core.ui.base.paging.PagingUiState
import com.stslex.core.ui.mvi.Store
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.feature.follower.navigation.FollowerScreenArgs
import com.stslex.feature.follower.ui.model.FollowerModel
import com.stslex.feature.follower.ui.store.FollowerStore.Action
import com.stslex.feature.follower.ui.store.FollowerStore.Event
import com.stslex.feature.follower.ui.store.FollowerStore.State

interface FollowerStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val type: FollowerScreenArgs,
        val paging: PagingUiState<FollowerModel>,
        val screen: FollowerScreenState,
        val query: String
    ) : Store.State {

        companion object {

            val INITIAL = State(
                type = FollowerScreenArgs.Follower(""),
                paging = PagingUiState.default(PagingConfig.DEFAULT),
                screen = FollowerScreenState.Shimmer,
                query = ""
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
        data object Load : Action

        @Stable
        data object Refresh : Action

        @Stable
        data object Retry : Action

        @Stable
        data class QueryChanged(val query: String) : Action

        @Stable
        data class OnUserClick(val uuid: String) : Action
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ShowSnackbar(val snackbar: Snackbar) : Event
    }

    sealed interface Navigation : Store.Navigation
}

package com.stslex.wizard.feature.follower.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.base.paging.PagingConfig
import com.stslex.wizard.core.ui.base.paging.PagingUiState
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.follower.ui.model.FollowerModel
import com.stslex.wizard.feature.follower.ui.store.FollowerStore.Action
import com.stslex.wizard.feature.follower.ui.store.FollowerStore.Event
import com.stslex.wizard.feature.follower.ui.store.FollowerStore.State

interface FollowerStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val type: Screen.Follower.FollowerType,
        val uuid: String,
        val paging: PagingUiState<FollowerModel>,
        val screen: FollowerScreenState,
        val query: String
    ) : Store.State {

        companion object {

            val INITIAL = State(
                type = Screen.Follower.FollowerType.FOLLOWER,
                uuid = "",
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
            val followerType: Screen.Follower.FollowerType,
            val uuid: String
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

        sealed interface Navigation : Action, Store.Action.Navigation
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ShowSnackbar(val snackbar: CommonEvents.Snackbar) : Event
    }
}

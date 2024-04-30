package com.stslex.feature.match.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingConfig
import com.stslex.core.ui.base.paging.PagingUiState
import com.stslex.core.ui.mvi.Store
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.core.ui.navigation.args.MatchScreenArgs
import com.stslex.feature.match.ui.model.MatchUiModel
import com.stslex.feature.match.ui.store.MatchStore.Action
import com.stslex.feature.match.ui.store.MatchStore.Event
import com.stslex.feature.match.ui.store.MatchStore.State

interface MatchStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val screen: MatchScreenState,
        val uuid: String,
        val isSelf: Boolean,
        val query: String,
        val paging: PagingUiState<MatchUiModel>
    ) : Store.State {

        companion object {

            val INITIAL = State(
                screen = MatchScreenState.Shimmer,
                paging = PagingUiState.default(PagingConfig.DEFAULT),
                uuid = "",
                isSelf = false,
                query = ""
            )
        }
    }

    @Stable
    sealed interface Event : Store.Event {

        data class ShowSnackbar(
            val snackbar: Snackbar
        ) : Event
    }

    @Stable
    sealed interface Action : Store.Action {

        data class Init(
            val args: MatchScreenArgs
        ) : Action

        data object Refresh : Action

        data object LoadMore : Action

        data class OnMatchClick(
            val matchUuid: String
        ) : Action

        data object OnRetryClick : Action

        data object Logout : Action

        data object RepeatLastAction : Action, Store.Action.RepeatLastAction

        data class OnQueryChanged(
            val query: String
        ) : Action
    }

    @Stable
    sealed interface Navigation : Store.Navigation {

        data class MatchDetails(val matchUuid: String) : Navigation

        data object LogOut : Navigation
    }
}


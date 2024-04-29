package com.stslex.feature.match.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingState
import com.stslex.core.ui.mvi.Store
import com.stslex.core.ui.mvi.Store.Event.Snackbar
import com.stslex.core.ui.navigation.args.MatchScreenArgs
import com.stslex.feature.match.ui.model.MatchUiModel
import com.stslex.feature.match.ui.store.MatchStore.Action
import com.stslex.feature.match.ui.store.MatchStore.Event
import com.stslex.feature.match.ui.store.MatchStore.State

// todo make more representative logic with store components implementation https://github.com/stslex/Wizard/issues/34
interface MatchStore : Store<State, Event, Action> {

    @Stable
    data class State(
        val screen: MatchScreenState,
        val uuid: String,
        val isSelf: Boolean,
        val pagingState: PagingState<MatchUiModel>
    ) : Store.State {

        companion object {

            val INITIAL = State(
                screen = MatchScreenState.Shimmer,
                pagingState = PagingState.default(),
                uuid = "",
                isSelf = false
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
    }

    @Stable
    sealed interface Navigation : Store.Navigation {

        data class MatchDetails(val matchUuid: String) : Navigation

        data object LogOut : Navigation
    }
}


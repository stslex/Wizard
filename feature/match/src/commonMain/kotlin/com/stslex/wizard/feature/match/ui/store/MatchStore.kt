package com.stslex.wizard.feature.match.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.navigation.v2.Config.BottomBar.Match.Type
import com.stslex.wizard.core.ui.kit.base.paging.PagingConfig
import com.stslex.wizard.core.ui.kit.base.paging.PagingUiState
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.match.ui.model.MatchUiModel
import com.stslex.wizard.feature.match.ui.store.MatchStore.Action
import com.stslex.wizard.feature.match.ui.store.MatchStore.Event
import com.stslex.wizard.feature.match.ui.store.MatchStore.State

interface MatchStore : Store<State, Action, Event> {

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
            val snackbar: CommonEvents.Snackbar
        ) : Event
    }

    @Stable
    sealed interface Action : Store.Action {

        data class Init(
            val type: Type,
            val uuid: String
        ) : Action

        data object Refresh : Action

        data object LoadMore : Action

        data class OnMatchClick(
            val matchUuid: String
        ) : Action

        data object OnRetryClick : Action

        data object Logout : Action

        data object RepeatLastAction : Action, Store.Action.RepeatLast

        data class OnQueryChanged(
            val query: String
        ) : Action


        @Stable
        sealed interface Navigation : Action, Store.Action.Navigation {

            data class MatchDetails(val uuid: String) : Navigation

            data object LogOut : Navigation
        }
    }
}


package com.stslex.wizard.feature.match.ui.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.kit.base.paging.PagingConfig
import com.stslex.wizard.core.ui.kit.base.paging.PagingUiState
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Action
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Event
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.State
import com.stslex.wizard.feature.match.ui.model.MatchUiModel

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

            fun init(
                uuid: String,
                isSelf: Boolean,
            ) = State(
                screen = MatchScreenState.Shimmer,
                paging = PagingUiState.default(PagingConfig.DEFAULT),
                uuid = uuid,
                isSelf = isSelf,
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

        @Stable
        sealed interface Paging : Action {

            @Stable
            data object Init : Paging

            @Stable
            data object Refresh : Paging

            @Stable
            data object LoadMore : Paging

            @Stable
            data object Retry : Paging
        }

        @Stable
        sealed interface Common : Action {

            @Stable
            data class MatchClick(val matchUuid: String) : Common

            @Stable
            data object Retry : Common

            @Stable
            data object Logout : Common

            @Stable
            data class QueryChanged(val query: String) : Common

            @Stable
            data object RepeatLastAction : Common, Store.Action.RepeatLast

            @Stable
            data class ShowError(val error: Throwable) : Common

        }

        @Stable
        sealed interface Navigation : Action, Store.Action.Navigation {

            data class MatchDetails(val uuid: String) : Navigation

            data object LogOut : Navigation
        }
    }
}


package com.stslex.wizard.feature.match.ui.store

import androidx.compose.runtime.Stable
import com.stslex.core.ui.base.paging.PagingConfig
import com.stslex.core.ui.base.paging.PagingUiState
import com.stslex.core.ui.mvi.StoreComponent
import com.stslex.core.ui.mvi.StoreComponent.Event.Snackbar
import com.stslex.core.ui.navigation.args.MatchScreenArgs
import com.stslex.wizard.feature.match.ui.model.MatchUiModel

interface MatchStoreComponent : StoreComponent {

    @Stable
    data class State(
        val screen: MatchScreenState,
        val uuid: String,
        val isSelf: Boolean,
        val query: String,
        val paging: PagingUiState<MatchUiModel>
    ) : StoreComponent.State {

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
    sealed interface Event : StoreComponent.Event {

        data class ShowSnackbar(
            val snackbar: Snackbar
        ) : Event
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

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

        data object RepeatLastAction : Action, StoreComponent.Action.RepeatLastAction

        data class OnQueryChanged(
            val query: String
        ) : Action
    }

    @Stable
    sealed interface Navigation : StoreComponent.Navigation {

        data class MatchDetails(val matchUuid: String) : Navigation

        data object LogOut : Navigation
    }
}


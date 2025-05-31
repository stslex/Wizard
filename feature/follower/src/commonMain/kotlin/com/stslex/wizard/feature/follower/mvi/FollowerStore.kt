package com.stslex.wizard.feature.follower.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.kit.base.paging.PagingConfig
import com.stslex.wizard.core.ui.kit.base.paging.PagingUiState
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Action
import com.stslex.wizard.feature.follower.mvi.FollowerStore.Event
import com.stslex.wizard.feature.follower.mvi.FollowerStore.State
import com.stslex.wizard.feature.follower.ui.model.FollowerModel

interface FollowerStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val type: Config.Follower.FollowerType,
        val uuid: String,
        val paging: PagingUiState<FollowerModel>,
        val screen: FollowerScreenState,
        val query: String
    ) : Store.State {

        companion object {

            fun initial(
                type: Config.Follower.FollowerType,
                uuid: String
            ) = State(
                type = type,
                uuid = uuid,
                paging = PagingUiState.default(PagingConfig.DEFAULT),
                screen = FollowerScreenState.Shimmer,
                query = ""
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        @Stable
        sealed interface Paging : Action {

            @Stable
            data object Init : Paging

            @Stable
            data object Load : Paging

            @Stable
            data object Refresh : Paging

            @Stable
            data object Retry : Paging
        }

        @Stable
        sealed interface Common : Action {

            @Stable
            data class OnUserClick(val uuid: String) : Common

            @Stable
            data class QueryChanged(val query: String) : Common

            @Stable
            data class ShowError(val error: Throwable) : Common
        }

        sealed interface Navigation : Action, Store.Action.Navigation
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ShowSnackbar(val snackbar: CommonEvents.Snackbar) : Event
    }
}

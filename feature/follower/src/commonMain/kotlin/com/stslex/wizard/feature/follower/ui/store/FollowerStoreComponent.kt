package com.stslex.wizard.feature.follower.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.base.paging.PagingConfig
import com.stslex.wizard.core.ui.base.paging.PagingUiState
import com.stslex.wizard.core.ui.mvi.StoreComponent
import com.stslex.wizard.core.ui.mvi.StoreComponent.Event.Snackbar
import com.stslex.wizard.feature.follower.navigation.FollowerScreenArgs
import com.stslex.wizard.feature.follower.ui.model.FollowerModel

interface FollowerStoreComponent : StoreComponent {

    @Stable
    data class State(
        val type: FollowerScreenArgs,
        val paging: PagingUiState<FollowerModel>,
        val screen: FollowerScreenState,
        val query: String
    ) : StoreComponent.State {

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
    sealed interface Action : StoreComponent.Action {

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
    sealed interface Event : StoreComponent.Event {

        @Stable
        data class ShowSnackbar(val snackbar: Snackbar) : Event
    }

    sealed interface Navigation : StoreComponent.Navigation
}

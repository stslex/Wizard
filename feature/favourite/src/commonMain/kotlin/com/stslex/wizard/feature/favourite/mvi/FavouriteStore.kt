package com.stslex.wizard.feature.favourite.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.kit.base.paging.PagingConfig
import com.stslex.wizard.core.ui.kit.base.paging.PagingUiState
import com.stslex.wizard.core.ui.mvi.CommonEvents
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Event
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.State
import com.stslex.wizard.feature.favourite.ui.model.FavouriteModel

interface FavouriteStore : Store<State, Action, Event> {

    @Stable
    data class State(
        val uuid: String,
        val query: String,
        val paging: PagingUiState<FavouriteModel>,
        val screen: FavouriteScreenState,
        val isLoading: Boolean
    ) : Store.State {

        companion object {

            fun initial(uuid: String) = State(
                uuid = uuid,
                query = "",
                paging = PagingUiState.default(PagingConfig.DEFAULT),
                screen = FavouriteScreenState.Shimmer,
                isLoading = true
            )
        }
    }

    @Stable
    sealed interface Action : Store.Action {

        sealed interface Paging : Action {

            @Stable
            data object Init : Paging

            @Stable
            data object LoadMore : Paging

            @Stable
            data object Refresh : Paging

            @Stable
            data object Retry : Paging
        }

        sealed interface Click : Action {

            @Stable
            data class LikeClick(val uuid: String) : Click

            @Stable
            data class ItemClick(val uuid: String) : Click
        }

        @Stable
        data class InputSearch(val query: String) : Action

        data class ShowError(val error: Throwable) : Action

        sealed interface Navigation : Action, Store.Action.Navigation {

            data class OpenFilm(val uuid: String) : Navigation
        }
    }

    @Stable
    sealed interface Event : Store.Event {

        @Stable
        data class ShowSnackbar(val snackbar: CommonEvents.Snackbar) : Event
    }
}
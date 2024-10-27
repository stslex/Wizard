package com.stslex.wizard.feature.film.ui.store

import androidx.compose.runtime.Stable
import com.stslex.wizard.core.ui.mvi.StoreComponent

interface FilmStoreComponent : StoreComponent {

    @Stable
    data class State(
        val filmId: String,
        val screenState: FilmScreenState
    ) : StoreComponent.State {

        companion object {
            val INITIAL = State(
                filmId = "",
                screenState = FilmScreenState.Loading
            )
        }
    }

    @Stable
    sealed interface Action : StoreComponent.Action {

        @Stable
        data class Init(val id: String) : Action

        data object BackButtonClick : Action

        data object LikeButtonClick : Action
    }

    sealed interface Event : StoreComponent.Event {

        data class ErrorSnackbar(val throwable: Throwable) : Event
    }

    sealed interface Navigation : StoreComponent.Navigation {

        data object Back : Navigation
    }
}
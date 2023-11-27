package com.stslex.feature.film.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.film.domain.interactor.FilmInteractor
import com.stslex.feature.film.navigation.FilmRouter
import com.stslex.feature.film.ui.model.toUi
import com.stslex.feature.film.ui.store.FilmStoreComponent.Action
import com.stslex.feature.film.ui.store.FilmStoreComponent.Event
import com.stslex.feature.film.ui.store.FilmStoreComponent.Navigation
import com.stslex.feature.film.ui.store.FilmStoreComponent.State

class FilmStore(
    private val interactor: FilmInteractor,
    appDispatcher: AppDispatcher,
    router: FilmRouter,
) : BaseStore<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {

    override fun sendAction(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
        }
    }

    private fun actionInit(action: Action.Init) {
        interactor
            .getFilm(action.id)
            .launch { film ->
                updateState { currentState ->
                    currentState.copy(
                        screenState = FilmScreenState.Content(film.toUi())
                    )
                }
            }
    }
}


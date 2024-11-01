package com.stslex.wizard.feature.film.ui.store

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.ui.mvi.Store
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.navigation.FilmRouter
import com.stslex.wizard.feature.film.ui.model.toDomain
import com.stslex.wizard.feature.film.ui.model.toUi
import com.stslex.wizard.feature.film.ui.store.FilmStoreComponent.Action
import com.stslex.wizard.feature.film.ui.store.FilmStoreComponent.Event
import com.stslex.wizard.feature.film.ui.store.FilmStoreComponent.Navigation
import com.stslex.wizard.feature.film.ui.store.FilmStoreComponent.State
import kotlinx.coroutines.Job

class FilmStore(
    private val interactor: FilmInteractor,
    appDispatcher: AppDispatcher,
    router: FilmRouter,
) : Store<State, Event, Action, Navigation>(
    router = router,
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
) {

    private var likeJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.BackButtonClick -> actionBackButtonClick()
            is Action.LikeButtonClick -> actionLikeButtonClick()
        }
    }

    private fun actionLikeButtonClick() {
        if (likeJob?.isActive == true) return
        val film = state.value.screenState.result ?: return
        updateState { state ->
            state.copy(
                screenState = FilmScreenState.Content(film.copy(isFavorite = !film.isFavorite))
            )
        }
        likeJob = launch(
            onError = {
                updateState { state ->
                    state.copy(
                        screenState = FilmScreenState.Content(film.copy(isFavorite = !film.isFavorite))
                    )
                }
                sendEvent(Event.ErrorSnackbar(it))
            },
            onSuccess = {
                // TODO show toast success
            }
        ) {
            if (film.isFavorite) {
                interactor.dislikeFilm(film.id)
            } else {
                interactor.likeFilm(film.toDomain())
            }
        }
    }

    private fun actionBackButtonClick() {
        consumeNavigation(Navigation.Back)
    }

    private fun actionInit(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(
                screenState = FilmScreenState.Loading,
                filmId = action.id
            )
        }
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

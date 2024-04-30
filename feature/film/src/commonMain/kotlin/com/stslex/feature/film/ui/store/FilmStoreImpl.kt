package com.stslex.feature.film.ui.store

import com.stslex.core.core.AppDispatcher
import com.stslex.core.ui.mvi.BaseStore
import com.stslex.feature.film.domain.interactor.FilmInteractor
import com.stslex.feature.film.navigation.FilmRouter
import com.stslex.feature.film.ui.model.toDomain
import com.stslex.feature.film.ui.model.toUi
import com.stslex.feature.film.ui.store.FilmStore.Action
import com.stslex.feature.film.ui.store.FilmStore.Event
import com.stslex.feature.film.ui.store.FilmStore.Navigation
import com.stslex.feature.film.ui.store.FilmStore.State
import kotlinx.coroutines.Job

class FilmStoreImpl(
    private val interactor: FilmInteractor,
    appDispatcher: AppDispatcher,
    router: FilmRouter,
) : FilmStore, BaseStore<State, Event, Action, Navigation>(
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
        navigate(Navigation.Back)
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

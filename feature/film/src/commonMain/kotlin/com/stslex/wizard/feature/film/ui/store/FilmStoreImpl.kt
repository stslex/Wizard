package com.stslex.wizard.feature.film.ui.store

import com.stslex.wizard.core.core.AppDispatcher
import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.navigation.FilmRouter
import com.stslex.wizard.feature.film.ui.model.toDomain
import com.stslex.wizard.feature.film.ui.model.toUi
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action
import com.stslex.wizard.feature.film.ui.store.FilmStore.Event
import com.stslex.wizard.feature.film.ui.store.FilmStore.State
import kotlinx.coroutines.Job

class FilmStoreImpl(
    private val interactor: FilmInteractor,
    appDispatcher: AppDispatcher,
    private val router: FilmRouter,
) : BaseStore<State, Action, Event>(
    appDispatcher = appDispatcher,
    initialState = State.INITIAL,
), FilmStore {

    private var likeJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.BackButtonClick -> actionBackButtonClick()
            is Action.LikeButtonClick -> actionLikeButtonClick()
            is Action.Navigation -> router(action)
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
        sendAction(Action.Navigation.Back)
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
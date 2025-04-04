package com.stslex.wizard.feature.film.ui.store

import com.stslex.wizard.core.ui.mvi.BaseStore
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.navigation.FilmComponent
import com.stslex.wizard.feature.film.ui.model.toDomain
import com.stslex.wizard.feature.film.ui.model.toUi
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action
import com.stslex.wizard.feature.film.ui.store.FilmStore.Event
import com.stslex.wizard.feature.film.ui.store.FilmStore.State
import kotlinx.coroutines.Job

class FilmStoreImpl(
    private val interactor: FilmInteractor,
    private val component: FilmComponent,
) : BaseStore<State, Action, Event>(State.INITIAL), FilmStore {

    private var likeJob: Job? = null

    override fun process(action: Action) {
        when (action) {
            is Action.Init -> actionInit(action)
            is Action.BackButtonClick -> actionBackButtonClick()
            is Action.LikeButtonClick -> actionLikeButtonClick()
            is Action.Navigation -> actionNavigation(action)
        }
    }

    private fun actionNavigation(action: Action.Navigation) {
        when (action) {
            Action.Navigation.Back -> component.back()
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
        consume(Action.Navigation.Back)
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

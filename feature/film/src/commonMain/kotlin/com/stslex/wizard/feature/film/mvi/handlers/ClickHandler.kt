package com.stslex.wizard.feature.film.mvi.handlers

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.film.di.FilmScope
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.mvi.FilmHandlerStore
import com.stslex.wizard.feature.film.mvi.FilmScreenState
import com.stslex.wizard.feature.film.mvi.FilmStore.Action
import com.stslex.wizard.feature.film.mvi.FilmStore.Action.Click
import com.stslex.wizard.feature.film.mvi.FilmStore.Event
import com.stslex.wizard.feature.film.ui.model.toDomain
import kotlinx.coroutines.Job
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FilmScope::class)
@Scoped
internal class ClickHandler(
    private val interactor: FilmInteractor
) : Handler<Click, FilmHandlerStore> {

    private var likeJob: Job? = null

    override fun FilmHandlerStore.invoke(action: Click) {
        when (action) {
            Click.BackButtonClick -> actionBackButtonClick()
            Click.LikeButtonClick -> actionLikeButtonClick()
        }
    }

    private fun FilmHandlerStore.actionLikeButtonClick() {
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

    private fun FilmHandlerStore.actionBackButtonClick() {
        consume(Action.Navigation.Back)
    }
}
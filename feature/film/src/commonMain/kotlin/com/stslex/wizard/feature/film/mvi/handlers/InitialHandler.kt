package com.stslex.wizard.feature.film.mvi.handlers

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.film.di.FilmScope
import com.stslex.wizard.feature.film.domain.interactor.FilmInteractor
import com.stslex.wizard.feature.film.mvi.FilmHandlerStore
import com.stslex.wizard.feature.film.mvi.FilmScreenState
import com.stslex.wizard.feature.film.mvi.FilmStore.Action
import com.stslex.wizard.feature.film.ui.model.toUi
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FilmScope::class)
@Scoped
internal class InitialHandler(
    private val interactor: FilmInteractor
) : Handler<Action.Init, FilmHandlerStore> {

    override fun FilmHandlerStore.invoke(action: Action.Init) {
        updateState { currentState ->
            currentState.copy(
                screenState = FilmScreenState.Loading,
            )
        }
        interactor
            .getFilm(state.value.uuid)
            .launch { film ->
                updateState { currentState ->
                    currentState.copy(
                        screenState = FilmScreenState.Content(film.toUi())
                    )
                }
            }
    }
}
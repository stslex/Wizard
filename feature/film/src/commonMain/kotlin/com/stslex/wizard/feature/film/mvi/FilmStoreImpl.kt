package com.stslex.wizard.feature.film.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.film.di.FilmScope
import com.stslex.wizard.feature.film.mvi.FilmStore.Action
import com.stslex.wizard.feature.film.mvi.FilmStore.Event
import com.stslex.wizard.feature.film.mvi.FilmStore.State
import com.stslex.wizard.feature.film.mvi.handlers.ClickHandler
import com.stslex.wizard.feature.film.mvi.handlers.FilmComponent
import com.stslex.wizard.feature.film.mvi.handlers.InitialHandler
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(FilmScope::class)
@Scoped
@Qualifier(FilmScope::class)
internal class FilmStoreImpl(
    component: FilmComponent,
    clickHandler: ClickHandler,
    initialHandler: InitialHandler,
    appDispatcher: AppDispatcher
) : FilmHandlerStore, BaseStore<State, Action, Event, FilmHandlerStore>(
    name = "FilmStore",
    initialState = State.initial(component.uuid),
    appDispatcher = appDispatcher,
    handlerCreator = { action ->
        when (action) {
            is Action.Click -> clickHandler
            is Action.Init -> initialHandler
            is Action.Navigation -> component
        }
    },
    initialActions = listOf(Action.Init)
)
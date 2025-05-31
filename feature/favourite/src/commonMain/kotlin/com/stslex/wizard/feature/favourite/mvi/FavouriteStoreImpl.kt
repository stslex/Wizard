package com.stslex.wizard.feature.favourite.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.favourite.di.FavouriteScope
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Event
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.State
import com.stslex.wizard.feature.favourite.mvi.handler.ActionClickHandler
import com.stslex.wizard.feature.favourite.mvi.handler.ActionInputSearchHandler
import com.stslex.wizard.feature.favourite.mvi.handler.ActionPagingHandler
import com.stslex.wizard.feature.favourite.mvi.handler.ActionShowErrorHandler
import com.stslex.wizard.feature.favourite.mvi.handler.FavouriteComponent
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(FavouriteScope::class)
@Scoped
@Qualifier(FavouriteScope::class)
internal class FavouriteStoreImpl(
    appDispatcher: AppDispatcher,
    component: FavouriteComponent,
    clickHandler: ActionClickHandler,
    inputHandler: ActionInputSearchHandler,
    pagingHandler: ActionPagingHandler,
    showErrorHandler: ActionShowErrorHandler
) : BaseStore<State, Action, Event, FavouriteHandlerStore>(
    name = "FavouriteStore",
    initialState = State.Companion.initial(component.uuid),
    appDispatcher = appDispatcher,
    handlerCreator = { action ->
        when (action) {
            is Action.Click -> clickHandler
            is Action.InputSearch -> inputHandler
            is Action.Navigation -> component
            is Action.Paging -> pagingHandler
            is Action.ShowError -> showErrorHandler
        }
    },
    initialActions = listOf(Action.Paging.Init)
), FavouriteHandlerStore

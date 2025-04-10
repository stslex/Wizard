package com.stslex.wizard.feature.film_feed.ui.store

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.film_feed.di.FilmFeedScope
import com.stslex.wizard.feature.film_feed.mvi.ClickersHandler
import com.stslex.wizard.feature.film_feed.mvi.LoadFilmsHandler
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.State
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.Qualifier
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@KoinViewModel
@Scope(FilmFeedScope::class)
@Scoped
@Qualifier(FilmFeedScope::class)
internal class FeedStoreImpl(
    private val component: FilmFeedComponent,
    private val feedClickersHandler: ClickersHandler,
    private val loadFilmsHandler: LoadFilmsHandler,
    appDispatcher: AppDispatcher
) : FeedHandlerStore, BaseStore<State, Action, Event, FeedHandlerStore>(
    name = TAG,
    initialState = State.INITIAL,
    handlerCreator = { action ->
        when (action) {
            is Action.Click.FilmClick -> feedClickersHandler
            is Action.LoadFilms -> loadFilmsHandler
            is Action.Navigation.Film -> component
        }
    },
    appDispatcher = appDispatcher,
    initialActions = listOf(Action.LoadFilms),
) {

    internal companion object {

        private const val TAG = "FeedStore"
    }
}
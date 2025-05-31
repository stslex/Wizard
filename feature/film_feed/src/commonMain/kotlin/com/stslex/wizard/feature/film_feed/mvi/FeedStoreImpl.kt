package com.stslex.wizard.feature.film_feed.mvi

import com.stslex.wizard.core.core.coroutine.AppDispatcher
import com.stslex.wizard.core.ui.mvi.v2.BaseStore
import com.stslex.wizard.feature.film_feed.di.FilmFeedScope
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent
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
) : FeedHandlerStore, BaseStore<FeedStore.State, FeedStore.Action, FeedStore.Event, FeedHandlerStore>(
    name = TAG,
    initialState = FeedStore.State.Companion.INITIAL,
    handlerCreator = { action ->
        when (action) {
            is FeedStore.Action.Click.FilmClick -> feedClickersHandler
            is FeedStore.Action.LoadFilms -> loadFilmsHandler
            is FeedStore.Action.Navigation.Film -> component
        }
    },
    appDispatcher = appDispatcher,
    initialActions = listOf(FeedStore.Action.LoadFilms),
) {

    internal companion object {

        private const val TAG = "FeedStore"
    }
}
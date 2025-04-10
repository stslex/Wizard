package com.stslex.wizard.feature.film_feed.mvi

import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.film_feed.di.FilmFeedScope
import com.stslex.wizard.feature.film_feed.ui.store.FeedHandlerStore
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action.Click
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FilmFeedScope::class)
@Scoped
internal class ClickersHandler : Handler<Click, FeedHandlerStore> {

    override fun FeedHandlerStore.invoke(action: Click) {
        when (action) {
            is Click.FilmClick -> onFilmClick(action)
        }
    }

    private fun FeedHandlerStore.onFilmClick(action: Click.FilmClick) {
        consume(Action.Navigation.Film(action.uuid))
    }
}
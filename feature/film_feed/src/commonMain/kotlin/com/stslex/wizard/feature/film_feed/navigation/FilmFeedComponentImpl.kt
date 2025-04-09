package com.stslex.wizard.feature.film_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.feature.film_feed.ui.store.FeedHandlerStore
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore

internal class FeedFilmComponentImpl(
    componentContext: ComponentContext,
    private val navigateTo: (config: Config) -> Unit
) : FilmFeedComponent, ComponentContext by componentContext {

    override fun FeedHandlerStore.invoke(action: FeedStore.Action.Navigation) {
        when (action) {
            is FeedStore.Action.Navigation.Film -> navigateTo(Config.Film(action.uuid))
        }
    }
}
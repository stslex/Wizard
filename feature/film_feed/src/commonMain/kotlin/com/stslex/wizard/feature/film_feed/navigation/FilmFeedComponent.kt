package com.stslex.wizard.feature.film_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.film_feed.mvi.FeedHandlerStore
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.Action.Navigation

interface FilmFeedComponent : Component, Handler<Navigation, FeedHandlerStore> {

    companion object {

        fun ComponentContext.createFilmFeedComponent(
            navigateTo: (config: Config) -> Unit
        ): FilmFeedComponent = FeedFilmComponentImpl(
            componentContext = this,
            navigateTo = navigateTo
        )
    }
}
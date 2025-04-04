package com.stslex.wizard.feature.film_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Config

class FeedFilmComponentImpl private constructor(
    componentContext: ComponentContext,
    private val navigateTo: (config: Config) -> Unit
) : FilmFeedComponent, ComponentContext by componentContext {

    override fun openFilm(id: String) {
        navigateTo(Config.Film(id))
    }

    companion object {

        fun ComponentContext.createFilmFeedComponent(
            navigateTo: (config: Config) -> Unit
        ): FilmFeedComponent = FeedFilmComponentImpl(
            componentContext = this,
            navigateTo = navigateTo
        )
    }
}
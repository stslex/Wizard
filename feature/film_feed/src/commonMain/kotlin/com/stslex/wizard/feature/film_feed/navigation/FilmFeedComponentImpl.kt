package com.stslex.wizard.feature.film_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config

internal class FeedFilmComponentImpl(
    componentContext: ComponentContext,
    private val navigateTo: (config: Config) -> Unit
) : FilmFeedComponent, ComponentContext by componentContext {

    override fun openFilm(id: String) {
        navigateTo(Config.Film(id))
    }
}
package com.stslex.wizard.feature.film_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component
import com.stslex.wizard.core.navigation.v2.Config

interface FilmFeedComponent : Component {

    fun openFilm(id: String)

    companion object {

        fun ComponentContext.createFilmFeedComponent(
            navigateTo: (config: Config) -> Unit
        ): FilmFeedComponent = FeedFilmComponentImpl(
            componentContext = this,
            navigateTo = navigateTo
        )
    }
}
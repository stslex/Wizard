package com.stslex.wizard.host

import com.arkivanov.decompose.Cancellation
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.stslex.wizard.core.navigation.v2.AuthComponent
import com.stslex.wizard.core.navigation.v2.Config
import com.stslex.wizard.feature.film.navigation.FilmComponent
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent

interface RootComponent {

    val stack: Value<ChildStack<Config, Child>>

    fun onConfigChanged(block: (Config) -> Unit): Cancellation

    fun onBottomAppBarClick(config: Config.BottomBar)

    sealed interface Child {

        data class Auth(
            val component: AuthComponent
        ) : Child

        data class FeedFilm(
            val component: FilmFeedComponent
        ) : Child

        data class Film(
            val component: FilmComponent,
            val filmId: String
        ) : Child
    }

}
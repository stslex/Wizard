package com.stslex.wizard.host

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.stslex.wizard.core.navigation.v2.Config
import com.stslex.wizard.feature.auth.navigation.AuthComponentImpl
import com.stslex.wizard.feature.film.navigation.FilmComponentImpl.Companion.createFilmComponent
import com.stslex.wizard.feature.film_feed.navigation.FeedFilmComponentImpl.Companion.createFilmFeedComponent
import com.stslex.wizard.host.RootComponent.Child

class DefaultRootComponent(
    componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        childFactory = ::child,
        handleBackButton = true,
        initialConfiguration = Config.BottomBar.FilmFeed
    )

    override val stack: Value<ChildStack<Config, Child>> = _stack

    override fun onConfigChanged(block: (Config) -> Unit) = stack.subscribe {
        block(it.active.configuration)
    }

    override fun onBottomAppBarClick(config: Config.BottomBar) {
        navigateTo(config)
    }

    private fun child(
        config: Config,
        context: ComponentContext
    ): Child = when (config) {
        is Config.BottomBar.FilmFeed -> Child.FeedFilm(
            context.createFilmFeedComponent(navigateTo = ::navigateTo)
        )

        is Config.BottomBar.Match -> TODO()
        is Config.BottomBar.Profile -> TODO()
        is Config.Auth -> Child.Auth(AuthComponentImpl(context))
        is Config.Favourite -> TODO()
        is Config.Film -> Child.Film(
            context.createFilmComponent(popBack = ::popBack),
            filmId = config.id
        )

        is Config.Follower -> TODO()
        is Config.MatchFeed -> TODO()
        is Config.Settings -> TODO()
    }

    @OptIn(DelicateDecomposeApi::class)
    private fun navigateTo(config: Config) {
        navigation.push(config)
    }

    private fun popBack() {
        navigation.pop()
    }
}

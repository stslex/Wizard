package com.stslex.wizard.host

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.navigate
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.feature.auth.mvi.handler.AuthComponent.Companion.createAuthComponent
import com.stslex.wizard.feature.favourite.mvi.handler.FavouriteComponent.Companion.createFavouriteComponent
import com.stslex.wizard.feature.film.mvi.handlers.FilmComponent.Companion.createFilmComponent
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent.Companion.createFilmFeedComponent
import com.stslex.wizard.feature.follower.mvi.handlers.FollowerComponent.Companion.createFollowerComponent
import com.stslex.wizard.feature.match.navigation.MatchComponent.Companion.createMatchComponent
import com.stslex.wizard.feature.match_feed.navigation.MatchDetailsComponent.Companion.createMatchDetailsComponent
import com.stslex.wizard.feature.profile.navigation.ProfileComponent.Companion.createProfileComponent
import com.stslex.wizard.feature.settings.navigation.SettingsComponent.Companion.createSettingsComponent
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
        is Config.BottomBar.FilmFeed -> Child.FeedFilm(context.createFilmFeedComponent(::navigateTo))

        is Config.BottomBar.Match -> Child.Match(
            component = context.createMatchComponent(::navigateTo),
            type = config.type,
            uuid = config.uuid
        )

        is Config.BottomBar.Profile -> Child.Profile(
            component = context.createProfileComponent(
                type = config.type,
                uuid = config.uuid,
                navTo = ::navigateTo,
                popBack = ::popBack
            ),
        )

        is Config.Auth -> Child.Auth(context.createAuthComponent(::navigateTo))
        is Config.Favourite -> Child.Favourite(
            component = context.createFavouriteComponent(
                uuid = config.uuid,
                navTo = ::navigateTo
            ),
        )

        is Config.Film -> Child.Film(
            context.createFilmComponent(
                uuid = config.uuid,
                popBack = ::popBack
            ),
        )

        is Config.Follower -> Child.Follower(
            component = context.createFollowerComponent(
                followerType = config.type,
                uuid = config.uuid
            ),
        )

        is Config.MatchDetails -> Child.MatchDetails(
            component = context.createMatchDetailsComponent(::navigateTo),
            uuid = config.uuid
        )

        is Config.Settings -> Child.Settings(
            context.createSettingsComponent(
                navTo = ::navigateTo,
                back = ::popBack
            )
        )
    }

    @OptIn(DelicateDecomposeApi::class)
    private fun navigateTo(config: Config) {
        navigation.navigate { currentStack ->
            if (config.isBackAllow) {
                currentStack + config
            } else {
                listOf(config)
            }
        }
    }

    private fun popBack() {
        navigation.pop()
    }
}

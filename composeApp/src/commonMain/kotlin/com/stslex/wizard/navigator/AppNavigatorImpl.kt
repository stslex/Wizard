package com.stslex.wizard.navigator

import cafe.adriel.voyager.navigator.Navigator
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.auth.ui.AuthScreen
import com.stslex.wizard.feature.favourite.FavouriteScreen
import com.stslex.wizard.feature.film.ui.FilmScreen
import com.stslex.wizard.feature.follower.navigation.FollowerScreenArgs
import com.stslex.wizard.feature.follower.ui.FollowerScreen
import com.stslex.feature.match.ui.MatchScreen
import com.stslex.feature.match_feed.ui.MatchFeedScreen
import com.stslex.wizard.feature.settings.ui.SettingsScreen
import com.stslex.wizard.main_screen.MainScreen

class AppNavigatorImpl : AppNavigator {

    private var _navigator: Navigator? = null
    private val navigator: Navigator
        get() = requireNotNull(_navigator)

    override fun setNavigator(navigator: Navigator) {
        _navigator = navigator
    }

    override val currentScreen: AppScreen?
        get() = when (val item = _navigator?.lastItemOrNull) {
            AuthScreen -> AppScreen.Auth
            MainScreen -> AppScreen.Main
            MatchFeedScreen -> AppScreen.MatchFeed
            is FilmScreen -> AppScreen.Film(item.id)
            else -> null
        }

    override fun navigate(
        screen: AppScreen
    ) {
        when (screen) {
            is AppScreen.Back -> navigator.pop()
            is AppScreen.Auth -> navigator.replaceAll(AuthScreen)
            is AppScreen.Main -> navigator.replaceAll(MainScreen)
            is AppScreen.Film -> navigator.push(FilmScreen(screen.id))
            is AppScreen.MatchFeed -> navigator.push(MatchFeedScreen)
            is AppScreen.Favourite -> navigator.push(FavouriteScreen(uuid = screen.uuid))
            is AppScreen.Followers -> navToFollowers(screen.uuid)
            is AppScreen.Following -> navToFollowing(screen.uuid)
            is AppScreen.Settings -> navigator.push(SettingsScreen)
            is AppScreen.MatchDetails -> TODO()
            is AppScreen.Match -> navigator.push(MatchScreen(args = screen.args))
        }
    }

    private fun navToFollowing(uuid: String) {
        navigator.push(FollowerScreen(args = FollowerScreenArgs.Following(uuid = uuid)))
    }

    private fun navToFollowers(uuid: String) {
        navigator.push(FollowerScreen(args = FollowerScreenArgs.Follower(uuid = uuid)))
    }
}
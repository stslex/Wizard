package navigator

import cafe.adriel.voyager.navigator.Navigator
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.auth.ui.AuthScreen
import com.stslex.feature.film.ui.FilmScreen
import com.stslex.feature.follower.ui.FollowerScreen
import com.stslex.feature.follower.ui.FollowerScreenArgs
import com.stslex.feature.match_feed.ui.MatchFeedScreen
import main_screen.MainScreen

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
            AppScreen.Back -> navigator.pop()
            AppScreen.Auth -> navigator.replaceAll(AuthScreen)
            AppScreen.Main -> navigator.replaceAll(MainScreen)
            is AppScreen.Film -> navigator.push(FilmScreen(screen.id))
            AppScreen.MatchFeed -> navigator.push(MatchFeedScreen)
            is AppScreen.Favourite -> TODO()
            is AppScreen.Followers -> navigator.push(
                FollowerScreen(
                    args = FollowerScreenArgs.Follower(
                        uuid = screen.uuid
                    )
                )
            )

            is AppScreen.Following -> navigator.push(
                FollowerScreen(
                    args = FollowerScreenArgs.Following(
                        uuid = screen.uuid
                    )
                )
            )
        }
    }
}
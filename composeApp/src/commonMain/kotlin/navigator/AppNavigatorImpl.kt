package navigator

import cafe.adriel.voyager.navigator.Navigator
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.auth.ui.AuthScreen
import com.stslex.feature.film.ui.FilmScreen
import com.stslex.feature.match_feed.ui.MatchFeedScreen
import main_screen.MainScreen

class AppNavigatorImpl : AppNavigator {

    private var _navigator: Navigator? = null
    private val navigator: Navigator
        get() = requireNotNull(_navigator)

    override fun setNavigator(navigator: Navigator) {
        _navigator = navigator
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
        }
    }
}
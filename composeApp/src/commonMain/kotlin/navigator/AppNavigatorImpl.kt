package navigator

import cafe.adriel.voyager.navigator.Navigator
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.film.ui.FilmScreen
import main_screen.MainScreen

// TODO NAVIGATION beetwen screens after tab crush application
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
            AppScreen.Main -> navigator.push(MainScreen)
            is AppScreen.Film -> navigator.push(FilmScreen(screen.id))
        }
    }
}
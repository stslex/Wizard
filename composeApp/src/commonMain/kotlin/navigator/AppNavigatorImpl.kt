package navigator

import cafe.adriel.voyager.navigator.Navigator
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.film.ui.FilmScreen
import main_screen.MainScreen

// TODO NAVIGATION beetwen screens after tab crush application
class AppNavigatorImpl(
    private val navigator: Lazy<Navigator>
) : AppNavigator {

    override fun navigate(
        screen: AppScreen
    ) {
        when (screen) {
            AppScreen.Back -> navigator.value.pop()
            AppScreen.Main -> navigator.value.push(MainScreen)
            is AppScreen.Film -> navigator.value.push(FilmScreen(screen.id))
        }
    }
}
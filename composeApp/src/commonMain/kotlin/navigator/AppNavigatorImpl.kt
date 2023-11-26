package navigator

import cafe.adriel.voyager.navigator.Navigator
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import main_screen.MainScreen

class AppNavigatorImpl(
    private val navigator: Lazy<Navigator>
) : AppNavigator {

    override fun navigate(
        screen: AppScreen
    ) {
        when (screen) {
            AppScreen.Main -> navigator.value.push(MainScreen)
        }
    }
}
import cafe.adriel.voyager.navigator.Navigator
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.home.ui.HomeScreen
import com.stslex.feature.home.ui.SecondScreen

class AppNavigatorImpl(
    private val navigator: Lazy<Navigator>
) : AppNavigator {

    override fun navigate(
        screen: AppScreen
    ) {
        when (screen) {
            AppScreen.Home -> navigator.value.push(HomeScreen)
            is AppScreen.SecondScreen -> navigator.value.push(SecondScreen(screen.text))
        }
    }
}
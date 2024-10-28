package com.stslex.wizard.core.navigation.navigator

import androidx.navigation.NavHostController
import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.navigation.Screen
import org.koin.core.annotation.Singleton

@Singleton
class NavigatorImpl(
    private val navHostController: NavHostController
) : Navigator {

    override fun navTo(screen: Screen, options: NavigatorOptions) {
        Logger.d("navigateTo screen: $screen, options: $options", TAG)
        val currentRoute = navHostController.currentDestination?.route ?: return
        try {
            navHostController.navigate(screen) {
                if (options.isSingleTop.not()) return@navigate

                popUpTo(currentRoute) {
                    inclusive = true
                    saveState = true
                }
                launchSingleTop = true
            }
        } catch (exception: Exception) {
            Logger.e(exception, TAG, "screen: $screen")
        }
    }

    override fun popBack() {
        Logger.d("process popBackStack", TAG)
        navHostController.popBackStack()
    }

    companion object {
        private const val TAG = "NAVIGATION"
    }
}
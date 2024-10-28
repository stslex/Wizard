package com.stslex.wizard.core.navigation.navigator

import androidx.navigation.NavHostController
import com.stslex.wizard.core.core.Logger
import com.stslex.wizard.core.navigation.Screen

class NavigatorImpl(private val controller: NavHostController) : Navigator {

    override fun navTo(
        screen: Screen,
        options: NavigatorOptions
    ) {
        Logger.d("navigateTo screen: $screen, options: $options", TAG)
        val currentRoute = controller.currentDestination?.route ?: return
        try {
            controller.navigate(screen) {
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
        controller.popBackStack()
    }

    companion object {
        private const val TAG = "NAVIGATION"
    }
}
package com.stslex.core.ui.navigation

import cafe.adriel.voyager.navigator.Navigator

interface AppNavigator {

    val currentScreen: AppScreen?

    fun navigate(screen: AppScreen)

    fun setNavigator(navigator: Navigator)
}
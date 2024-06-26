package com.stslex.feature.settings.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.settings.ui.store.SettingsStore.Navigation

class SettingsRouterImpl(
    private val navigator: AppNavigator
) : SettingsRouter {

    override fun invoke(
        event: Navigation
    ) {
        when (event) {
            Navigation.Back -> navigator.navigate(AppScreen.Back)
            Navigation.LogOut -> navigator.navigate(AppScreen.Auth)
        }
    }
}
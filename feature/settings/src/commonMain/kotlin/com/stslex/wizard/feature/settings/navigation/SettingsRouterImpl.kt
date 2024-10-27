package com.stslex.wizard.feature.settings.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.wizard.feature.settings.ui.store.SettingsStoreComponent.Navigation

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
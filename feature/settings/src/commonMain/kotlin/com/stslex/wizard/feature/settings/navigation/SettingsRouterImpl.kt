package com.stslex.wizard.feature.settings.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.settings.ui.store.SettingsStore.Action.Navigation

class SettingsRouterImpl(private val navigator: Navigator) : SettingsRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            Navigation.Back -> navigator.popBack()
            Navigation.LogOut -> navigator.navTo(Screen.Auth)
        }
    }
}
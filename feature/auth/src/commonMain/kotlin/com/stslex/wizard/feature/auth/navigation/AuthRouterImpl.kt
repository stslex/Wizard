package com.stslex.wizard.feature.auth.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.auth.ui.store.AuthStoreComponent.Navigation

class AuthRouterImpl(
    private val navigator: Navigator
) : AuthRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.HomeFeature -> navigator.navigateTo(Screen.Main)
        }
    }
}
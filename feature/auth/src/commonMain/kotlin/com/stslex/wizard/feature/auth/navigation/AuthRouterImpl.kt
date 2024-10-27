package com.stslex.wizard.feature.auth.navigation

import com.stslex.wizard.core.ui.navigation.AppNavigator
import com.stslex.wizard.core.ui.navigation.AppScreen
import com.stslex.wizard.feature.auth.ui.store.AuthStoreComponent.Navigation

class AuthRouterImpl(
    private val navigator: AppNavigator
) : AuthRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.HomeFeature -> navigator.navigate(AppScreen.Main)
        }
    }
}
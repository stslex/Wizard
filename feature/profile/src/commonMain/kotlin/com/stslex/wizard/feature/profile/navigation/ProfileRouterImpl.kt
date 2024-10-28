package com.stslex.wizard.feature.profile.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.profile.ui.store.ProfileStoreComponent.Navigation

class ProfileRouterImpl(
    private val navigator: Navigator
) : ProfileRouter {

    override fun invoke(
        event: Navigation
    ) {
        when (event) {
            Navigation.LogIn -> navigator.navTo(Screen.Auth)
            Navigation.Back -> navigator.popBack()
            Navigation.Settings -> navigator.navTo(Screen.Settings)
            is Navigation.Favourite -> navigator.navTo(Screen.Favourite(uuid = event.uuid))
            is Navigation.Following -> navigator.navTo(Screen.Following(uuid = event.uuid))
            is Navigation.Followers -> navigator.navTo(Screen.Followers(uuid = event.uuid))
        }
    }
}
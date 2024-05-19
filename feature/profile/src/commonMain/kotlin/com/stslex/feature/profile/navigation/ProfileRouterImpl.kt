package com.stslex.feature.profile.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.profile.ui.store.ProfileStoreComponent.Navigation

class ProfileRouterImpl(
    private val navigator: AppNavigator
) : ProfileRouter {

    override fun invoke(
        event: Navigation
    ) {
        when (event) {
            Navigation.LogIn -> navigator.navigate(AppScreen.Auth)
            Navigation.Back -> navigator.navigate(AppScreen.Back)
            Navigation.Settings -> navigator.navigate(AppScreen.Settings)
            is Navigation.Favourite -> navigator.navigate(AppScreen.Favourite(uuid = event.uuid))
            is Navigation.Following -> navigator.navigate(AppScreen.Following(uuid = event.uuid))
            is Navigation.Followers -> navigator.navigate(AppScreen.Followers(uuid = event.uuid))
        }
    }
}
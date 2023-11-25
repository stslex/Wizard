package com.stslex.feature.home.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.core.ui.navigation.Router
import com.stslex.feature.home.ui.store.HomeScreenStoreComponent.Navigation

interface HomeScreenRouter : Router<Navigation>

class HomeScreenRouterImpl(
    private val navigator: AppNavigator
) : HomeScreenRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.SecondScreen -> navigator.navigate(AppScreen.SecondScreen(event.text))
        }
    }
}
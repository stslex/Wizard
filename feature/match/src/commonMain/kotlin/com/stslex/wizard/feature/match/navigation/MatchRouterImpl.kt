package com.stslex.wizard.feature.match.navigation

import com.stslex.wizard.core.ui.navigation.AppScreen
import com.stslex.wizard.feature.match.ui.store.MatchStoreComponent.Navigation

class MatchRouterImpl(
    private val navigator: AppNavigator
) : MatchRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.MatchDetails -> navigateToMatchDetails(event.matchUuid)
            is Navigation.LogOut -> navigator.navigate(AppScreen.Auth)
        }
    }

    private fun navigateToMatchDetails(matchUuid: String) {
        navigator.navigate(AppScreen.MatchDetails(matchUuid))
    }
}
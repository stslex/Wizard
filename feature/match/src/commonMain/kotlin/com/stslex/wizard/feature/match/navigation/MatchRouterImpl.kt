package com.stslex.wizard.feature.match.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.match.ui.store.MatchStoreComponent.Navigation

class MatchRouterImpl(private val navigator: Navigator) : MatchRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.MatchDetails -> navigator.navTo(Screen.MatchDetails(event.matchUuid))
            is Navigation.LogOut -> navigator.navTo(Screen.Auth)
        }
    }
}
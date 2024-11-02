package com.stslex.wizard.feature.match_feed.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.Action.Navigation

class MatchFeedRouterImpl(
    private val navigator: Navigator
) : MatchFeedRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.Film -> navigator.navTo(Screen.Film(event.uuid))
        }
    }
}
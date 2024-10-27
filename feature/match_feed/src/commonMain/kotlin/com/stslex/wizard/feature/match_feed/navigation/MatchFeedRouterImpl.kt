package com.stslex.wizard.feature.match_feed.navigation

import com.stslex.wizard.core.ui.navigation.AppNavigator
import com.stslex.wizard.core.ui.navigation.AppScreen
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreComponent.Navigation

class MatchFeedRouterImpl(
    private val navigator: AppNavigator
) : MatchFeedRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.Film -> navigateToFilm(event)
        }
    }

    private fun navigateToFilm(event: Navigation.Film) {
        navigator.navigate(AppScreen.Film(event.uuid))
    }
}
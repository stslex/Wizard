package com.stslex.feature.match_feed.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.match_feed.ui.store.MatchFeedStore.Navigation

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
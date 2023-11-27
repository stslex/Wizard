package com.stslex.feature.feed.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent

class FeedScreenRouterImpl(
    private val navigator: AppNavigator
) : FeedScreenRouter {

    override fun invoke(event: FeedScreenStoreComponent.Navigation) {
        when (event) {
            is FeedScreenStoreComponent.Navigation.Film -> navigateToFilm(event)
        }
    }

    private fun navigateToFilm(event: FeedScreenStoreComponent.Navigation.Film) {
        navigator.navigate(AppScreen.Film(event.filmId))
    }
}
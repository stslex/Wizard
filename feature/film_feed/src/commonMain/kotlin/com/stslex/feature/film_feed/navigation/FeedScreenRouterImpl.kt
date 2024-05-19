package com.stslex.feature.film_feed.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.film_feed.ui.store.FeedStoreComponent

class FeedScreenRouterImpl(
    private val navigator: AppNavigator
) : FeedScreenRouter {

    override fun invoke(event: FeedStoreComponent.Navigation) {
        when (event) {
            is FeedStoreComponent.Navigation.Film -> navigateToFilm(event)
        }
    }

    private fun navigateToFilm(event: FeedStoreComponent.Navigation.Film) {
        navigator.navigate(AppScreen.Film(event.filmId))
    }
}
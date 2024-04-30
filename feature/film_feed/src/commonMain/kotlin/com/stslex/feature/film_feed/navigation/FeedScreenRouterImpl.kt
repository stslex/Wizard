package com.stslex.feature.film_feed.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.film_feed.ui.store.FeedStore

class FeedScreenRouterImpl(
    private val navigator: AppNavigator
) : FeedScreenRouter {

    override fun invoke(event: FeedStore.Navigation) {
        when (event) {
            is FeedStore.Navigation.Film -> navigateToFilm(event)
        }
    }

    private fun navigateToFilm(event: FeedStore.Navigation.Film) {
        navigator.navigate(AppScreen.Film(event.filmId))
    }
}
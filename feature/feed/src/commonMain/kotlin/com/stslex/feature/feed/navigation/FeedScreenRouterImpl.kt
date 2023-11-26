package com.stslex.feature.feed.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.feature.feed.ui.store.FeedScreenStoreComponent

class FeedScreenRouterImpl(
    private val navigator: AppNavigator
) : FeedScreenRouter {

    override fun invoke(event: FeedScreenStoreComponent.Navigation) {
        when (event) {
            is FeedScreenStoreComponent.Navigation.Film -> {
// TODO                navigator.navigateToFilmDetails(event.filmId)
            }
        }
    }
}
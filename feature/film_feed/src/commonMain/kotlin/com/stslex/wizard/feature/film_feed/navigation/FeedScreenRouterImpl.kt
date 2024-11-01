package com.stslex.wizard.feature.film_feed.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.film_feed.ui.store.FeedStoreComponent

class FeedScreenRouterImpl(
    private val navigator: Navigator
) : FeedScreenRouter {

    override fun invoke(event: FeedStoreComponent.Navigation) {
        when (event) {
            is FeedStoreComponent.Navigation.Film -> navigator.navTo(Screen.Film(event.filmId))
        }
    }
}
package com.stslex.feature.favourite.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.favourite.ui.store.FavouriteStore.Navigation
import com.stslex.feature.favourite.ui.store.FavouriteStore.Navigation.OpenFilm

class FavouriteRouterImpl(
    private val navigator: AppNavigator
) : FavouriteRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is OpenFilm -> navigator.navigate(AppScreen.Film(id = event.uuid))
        }
    }
}
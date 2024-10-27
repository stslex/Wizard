package com.stslex.wizard.feature.favourite.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStoreComponent.Navigation
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStoreComponent.Navigation.OpenFilm

class FavouriteRouterImpl(
    private val navigator: AppNavigator
) : FavouriteRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is OpenFilm -> navigator.navigate(AppScreen.Film(id = event.uuid))
        }
    }
}
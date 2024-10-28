package com.stslex.wizard.feature.favourite.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStoreComponent.Navigation
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStoreComponent.Navigation.OpenFilm

class FavouriteRouterImpl(private val navigator: Navigator) : FavouriteRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is OpenFilm -> navigator.navTo(Screen.Film(id = event.uuid))
        }
    }
}
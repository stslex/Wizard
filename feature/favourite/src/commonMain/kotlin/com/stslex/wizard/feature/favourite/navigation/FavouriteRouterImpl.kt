package com.stslex.wizard.feature.favourite.navigation

import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Action.Navigation

class FavouriteRouterImpl(private val navigator: Navigator) : FavouriteRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.OpenFilm -> navigator.navTo(Screen.Film(id = event.uuid))
        }
    }
}
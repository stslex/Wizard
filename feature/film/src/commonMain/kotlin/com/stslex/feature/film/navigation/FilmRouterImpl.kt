package com.stslex.feature.film.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.film.ui.store.FilmStoreComponent

class FilmRouterImpl(
    private val navigator: AppNavigator
) : FilmRouter {

    override fun invoke(
        event: FilmStoreComponent.Navigation
    ) {
        when (event) {
            is FilmStoreComponent.Navigation.Back -> navigator.navigate(AppScreen.Back)
        }
    }
}
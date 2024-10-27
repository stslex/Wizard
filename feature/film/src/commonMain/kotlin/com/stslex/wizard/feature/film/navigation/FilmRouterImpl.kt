package com.stslex.wizard.feature.film.navigation

import com.stslex.wizard.core.ui.navigation.AppNavigator
import com.stslex.wizard.core.ui.navigation.AppScreen
import com.stslex.wizard.feature.film.ui.store.FilmStoreComponent

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
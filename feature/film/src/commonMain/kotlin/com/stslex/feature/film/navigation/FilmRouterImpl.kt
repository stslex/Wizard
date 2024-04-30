package com.stslex.feature.film.navigation

import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.film.ui.store.FilmStore

class FilmRouterImpl(
    private val navigator: AppNavigator
) : FilmRouter {

    override fun invoke(
        event: FilmStore.Navigation
    ) {
        when (event) {
            is FilmStore.Navigation.Back -> navigator.navigate(AppScreen.Back)
        }
    }
}
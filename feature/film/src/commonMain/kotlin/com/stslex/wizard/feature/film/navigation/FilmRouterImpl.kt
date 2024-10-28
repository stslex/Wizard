package com.stslex.wizard.feature.film.navigation

import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.film.ui.store.FilmStoreComponent

class FilmRouterImpl(
    private val navigator: Navigator
) : FilmRouter {

    override fun invoke(
        event: FilmStoreComponent.Navigation
    ) {
        when (event) {
            is FilmStoreComponent.Navigation.Back -> navigator.popBack()
        }
    }
}
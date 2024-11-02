package com.stslex.wizard.feature.film.navigation

import com.stslex.wizard.core.navigation.navigator.Navigator
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action.Navigation

class FilmRouterImpl(
    private val navigator: Navigator
) : FilmRouter {

    override fun invoke(event: Navigation) {
        when (event) {
            is Navigation.Back -> navigator.popBack()
        }
    }
}
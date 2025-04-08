package com.stslex.wizard.feature.favourite.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config

internal class FavouriteComponentImpl(
    context: ComponentContext,
    override val uuid: String,
    private val navTo: (Config) -> Unit
) : FavouriteComponent, ComponentContext by context {

    override fun openFilm(uuid: String) {
        navTo(Config.Film(uuid))
    }
}
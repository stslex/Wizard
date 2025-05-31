package com.stslex.wizard.feature.favourite.mvi.handler

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.navigation.Config.Film
import com.stslex.wizard.feature.favourite.mvi.FavouriteHandlerStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action.Navigation

internal class FavouriteComponentImpl(
    context: ComponentContext,
    override val uuid: String,
    private val navTo: (Config) -> Unit
) : FavouriteComponent, ComponentContext by context {

    override fun FavouriteHandlerStore.invoke(action: Navigation) {
        when (action) {
            is Navigation.OpenFilm -> navTo(Film(uuid))
        }
    }
}
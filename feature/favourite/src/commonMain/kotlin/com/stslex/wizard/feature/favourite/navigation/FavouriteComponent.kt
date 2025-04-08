package com.stslex.wizard.feature.favourite.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component
import com.stslex.wizard.core.navigation.v2.Config

interface FavouriteComponent : Component {

    fun openFilm(uuid: String)

    companion object {

        fun ComponentContext.createFavouriteComponent(
            navTo: (Config) -> Unit,
        ): FavouriteComponent = FavouriteComponentImpl(
            context = this,
            navTo = navTo,
        )
    }
}
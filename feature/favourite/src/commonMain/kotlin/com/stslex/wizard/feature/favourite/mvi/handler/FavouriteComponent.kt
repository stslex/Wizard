package com.stslex.wizard.feature.favourite.mvi.handler

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.favourite.mvi.FavouriteHandlerStore
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action.Navigation

interface FavouriteComponent : Component, Handler<Navigation, FavouriteHandlerStore> {

    val uuid: String

    companion object {

        fun ComponentContext.createFavouriteComponent(
            uuid: String,
            navTo: (Config) -> Unit,
        ): FavouriteComponent = FavouriteComponentImpl(
            context = this,
            uuid = uuid,
            navTo = navTo,
        )
    }
}
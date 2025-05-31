package com.stslex.wizard.feature.film.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.film.mvi.FilmHandlerStore
import com.stslex.wizard.feature.film.mvi.FilmStore

interface FilmComponent : Component, Handler<FilmStore.Action.Navigation, FilmHandlerStore> {

    val uuid: String

    companion object {

        fun ComponentContext.createFilmComponent(
            uuid: String,
            popBack: () -> Unit
        ): FilmComponent = FilmComponentImpl(
            componentContext = this,
            uuid = uuid,
            popBack = popBack
        )
    }
}


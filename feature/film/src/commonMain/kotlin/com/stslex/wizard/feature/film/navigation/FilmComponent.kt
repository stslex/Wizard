package com.stslex.wizard.feature.film.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component

interface FilmComponent : Component {

    val uuid: String

    fun back()

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


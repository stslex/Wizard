package com.stslex.wizard.feature.film.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component

interface FilmComponent : Component {

    fun back()

    companion object {

        fun ComponentContext.createFilmComponent(
            popBack: () -> Unit
        ): FilmComponent = FilmComponentImpl(
            componentContext = this,
            popBack = popBack
        )
    }
}


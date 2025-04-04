package com.stslex.wizard.feature.film.navigation

import com.arkivanov.decompose.ComponentContext

class FilmComponentImpl private constructor(
    componentContext: ComponentContext,
    private val popBack: () -> Unit,
) : FilmComponent, ComponentContext by componentContext {

    override fun back() {
        popBack()
    }

    companion object {

        fun ComponentContext.createFilmComponent(
            popBack: () -> Unit
        ): FilmComponent = FilmComponentImpl(
            componentContext = this,
            popBack = popBack
        )
    }
}
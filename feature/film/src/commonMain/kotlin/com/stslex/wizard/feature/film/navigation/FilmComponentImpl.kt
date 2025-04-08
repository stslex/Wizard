package com.stslex.wizard.feature.film.navigation

import com.arkivanov.decompose.ComponentContext

internal class FilmComponentImpl(
    componentContext: ComponentContext,
    override val uuid: String,
    private val popBack: () -> Unit,
) : FilmComponent, ComponentContext by componentContext {

    override fun back() {
        popBack()
    }
}
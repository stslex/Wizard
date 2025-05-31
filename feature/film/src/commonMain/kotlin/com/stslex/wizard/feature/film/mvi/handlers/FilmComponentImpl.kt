package com.stslex.wizard.feature.film.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.feature.film.mvi.FilmHandlerStore
import com.stslex.wizard.feature.film.mvi.FilmStore.Action

internal class FilmComponentImpl(
    componentContext: ComponentContext,
    override val uuid: String,
    private val popBack: () -> Unit,
) : FilmComponent, ComponentContext by componentContext {

    override fun FilmHandlerStore.invoke(action: Action.Navigation) {
        when (action) {
            Action.Navigation.Back -> popBack()
        }
    }
}
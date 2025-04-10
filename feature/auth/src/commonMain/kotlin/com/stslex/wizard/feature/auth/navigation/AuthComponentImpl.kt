package com.stslex.wizard.feature.auth.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config

internal class AuthComponentImpl(
    componentContext: ComponentContext,
    private val navTo: (Config) -> Unit
) : AuthComponent, ComponentContext by componentContext {

    override fun navToFilmFeed() {
        navTo(Config.BottomBar.FilmFeed)
    }

}

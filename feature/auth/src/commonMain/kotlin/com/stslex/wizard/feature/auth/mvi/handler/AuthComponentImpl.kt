package com.stslex.wizard.feature.auth.mvi.handler

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.feature.auth.mvi.AuthHandlerStore
import com.stslex.wizard.feature.auth.mvi.AuthStore
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action

internal class AuthComponentImpl(
    componentContext: ComponentContext,
    private val navTo: (Config) -> Unit
) : AuthComponent, ComponentContext by componentContext {

    override fun AuthHandlerStore.invoke(action: Action.Navigation) {
        when (action) {
            Action.Navigation.HomeFeature -> navTo(Config.BottomBar.FilmFeed)
        }
    }

}
package com.stslex.wizard.feature.auth.mvi.handler

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.auth.mvi.AuthHandlerStore
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action.Navigation

@Stable
interface AuthComponent : Component, Handler<Navigation, AuthHandlerStore> {

    companion object {

        fun ComponentContext.createAuthComponent(
            navTo: (Config) -> Unit
        ): AuthComponent = AuthComponentImpl(
            componentContext = this,
            navTo = navTo
        )
    }
}
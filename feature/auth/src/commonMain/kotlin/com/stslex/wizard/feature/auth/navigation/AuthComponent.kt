package com.stslex.wizard.feature.auth.navigation

import androidx.compose.runtime.Stable
import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component
import com.stslex.wizard.core.navigation.v2.Config

@Stable
interface AuthComponent : Component {

    fun navToFilmFeed()

    companion object {

        fun ComponentContext.createAuthComponent(
            navTo: (Config) -> Unit
        ): AuthComponent = AuthComponentImpl(
            componentContext = this,
            navTo = navTo
        )
    }
}


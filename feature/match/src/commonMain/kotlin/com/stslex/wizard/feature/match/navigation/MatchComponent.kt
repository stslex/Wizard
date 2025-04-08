package com.stslex.wizard.feature.match.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component
import com.stslex.wizard.core.navigation.v2.Config

interface MatchComponent : Component {

    fun openAuth()

    fun openMatchDetails(uuid: String)

    companion object {

        fun ComponentContext.createMatchComponent(
            navTo: (Config) -> Unit,
        ): MatchComponent = MatchComponentImpl(
            context = this,
            navTo = navTo,
        )
    }
}



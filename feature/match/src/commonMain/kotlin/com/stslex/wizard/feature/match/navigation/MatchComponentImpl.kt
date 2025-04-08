package com.stslex.wizard.feature.match.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Config

internal class MatchComponentImpl(
    context: ComponentContext,
    private val navTo: (Config) -> Unit,
) : MatchComponent, ComponentContext by context {

    override fun openAuth() {
        navTo(Config.Auth)
    }

    override fun openMatchDetails(uuid: String) {
        navTo(Config.MatchDetails(uuid))
    }
}
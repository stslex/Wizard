package com.stslex.wizard.feature.match.ui.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.feature.match.ui.mvi.MatchHandlerStore
import com.stslex.wizard.feature.match.ui.mvi.MatchStore

internal class MatchComponentImpl(
    context: ComponentContext,
    override val type: Config.BottomBar.Match.Type,
    override val uuid: String,
    private val navTo: (Config) -> Unit,
) : MatchComponent, ComponentContext by context {

    override fun MatchHandlerStore.invoke(action: MatchStore.Action.Navigation) {
        when (action) {
            MatchStore.Action.Navigation.LogOut -> navTo(Config.Auth)
            is MatchStore.Action.Navigation.MatchDetails -> navTo(Config.MatchDetails(uuid))
        }
    }
}
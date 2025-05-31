package com.stslex.wizard.feature.match.ui.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.navigation.Config.BottomBar.Match.Type
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.match.ui.mvi.MatchHandlerStore
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Action

interface MatchComponent : Component, Handler<Action.Navigation, MatchHandlerStore> {

    val type: Type
    val uuid: String

    companion object {

        fun ComponentContext.createMatchComponent(
            type: Type,
            uuid: String,
            navTo: (Config) -> Unit,
        ): MatchComponent = MatchComponentImpl(
            context = this,
            navTo = navTo,
            type = type,
            uuid = uuid,
        )
    }
}



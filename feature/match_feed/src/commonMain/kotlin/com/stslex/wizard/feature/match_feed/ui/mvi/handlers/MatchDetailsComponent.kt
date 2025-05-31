package com.stslex.wizard.feature.match_feed.ui.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedHandlerStore
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action

interface MatchDetailsComponent : Component, Handler<Action.Navigation, MatchFeedHandlerStore> {

    val uuid: String

    companion object {

        fun ComponentContext.createMatchDetailsComponent(
            uuid: String,
            navTo: (Config) -> Unit,
        ): MatchDetailsComponent = MatchDetailsComponentImpl(
            context = this,
            navTo = navTo,
            uuid = uuid
        )
    }
}


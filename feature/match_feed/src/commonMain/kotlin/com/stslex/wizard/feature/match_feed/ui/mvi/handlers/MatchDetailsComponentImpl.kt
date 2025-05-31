package com.stslex.wizard.feature.match_feed.ui.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedHandlerStore
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore

internal class MatchDetailsComponentImpl(
    context: ComponentContext,
    override val uuid: String,
    private val navTo: (Config) -> Unit,
) : MatchDetailsComponent, ComponentContext by context {

    override fun MatchFeedHandlerStore.invoke(action: MatchFeedStore.Action.Navigation) {
        when (action) {
            is MatchFeedStore.Action.Navigation.Film -> navTo(Config.Film(action.uuid))
        }
    }
}
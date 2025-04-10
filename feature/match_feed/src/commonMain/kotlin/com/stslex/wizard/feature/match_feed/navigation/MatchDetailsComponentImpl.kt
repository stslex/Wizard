package com.stslex.wizard.feature.match_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config

internal class MatchDetailsComponentImpl(
    context: ComponentContext,
    private val navTo: (Config) -> Unit,
) : MatchDetailsComponent, ComponentContext by context {

    override fun openFilm(uuid: String) {
        navTo(Config.Film(uuid))
    }
}
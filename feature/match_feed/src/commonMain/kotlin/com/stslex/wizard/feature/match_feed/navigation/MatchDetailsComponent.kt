package com.stslex.wizard.feature.match_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config

interface MatchDetailsComponent : Component {

    fun openFilm(uuid: String)

    companion object {

        fun ComponentContext.createMatchDetailsComponent(
            navTo: (Config) -> Unit,
        ): MatchDetailsComponent {
            return MatchDetailsComponentImpl(this, navTo)
        }
    }
}


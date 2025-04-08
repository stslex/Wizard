package com.stslex.wizard.feature.match_feed.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component
import com.stslex.wizard.core.navigation.v2.Config

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


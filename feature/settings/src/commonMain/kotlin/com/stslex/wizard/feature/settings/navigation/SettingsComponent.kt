package com.stslex.wizard.feature.settings.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Component
import com.stslex.wizard.core.navigation.v2.Config

interface SettingsComponent : Component {

    fun back()

    fun openAuth()

    companion object {

        fun ComponentContext.createSettingsComponent(
            navTo: (Config) -> Unit,
            back: () -> Unit
        ): SettingsComponent = SettingsComponentImpl(
            context = this,
            navTo = navTo,
            popBack = back
        )
    }
}


package com.stslex.wizard.feature.settings.ui.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.mvi.v2.Handler
import com.stslex.wizard.feature.settings.ui.mvi.SettingsHandlerStore
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Action

interface SettingsComponent : Component, Handler<Action.Navigation, SettingsHandlerStore> {

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


package com.stslex.wizard.feature.settings.ui.mvi.handlers

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.feature.settings.ui.mvi.SettingsHandlerStore
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Action

internal class SettingsComponentImpl(
    context: ComponentContext,
    private val navTo: (Config) -> Unit,
    private val popBack: () -> Unit
) : SettingsComponent, ComponentContext by context {

    override fun SettingsHandlerStore.invoke(action: Action.Navigation) {
        when (action) {
            Action.Navigation.Back -> popBack()
            Action.Navigation.LogOut -> navTo(Config.Auth)
        }
    }
}
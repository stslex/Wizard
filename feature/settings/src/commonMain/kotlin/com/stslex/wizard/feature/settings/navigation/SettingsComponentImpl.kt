package com.stslex.wizard.feature.settings.navigation

import com.arkivanov.decompose.ComponentContext
import com.stslex.wizard.core.navigation.v2.Config

internal class SettingsComponentImpl(
    context: ComponentContext,
    private val navTo: (Config) -> Unit,
    private val popBack: () -> Unit
) : SettingsComponent, ComponentContext by context {

    override fun back() {
        popBack()
    }

    override fun openAuth() {
        navTo(Config.Auth)
    }
}
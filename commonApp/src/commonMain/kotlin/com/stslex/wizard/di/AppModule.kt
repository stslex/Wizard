package com.stslex.wizard.di

import com.stslex.wizard.core.core.ModuleCore
import com.stslex.wizard.core.core.modules
import com.stslex.wizard.core.database.di.ModuleCoreDatabase
import com.stslex.wizard.core.network.api.di.ModuleCoreNetworkApi
import com.stslex.wizard.core.network.client.di.ModuleCoreNetworkClient
import com.stslex.wizard.core.ui.kit.di.ModuleCoreUi
import com.stslex.wizard.feature.settings.di.ModuleFeatureSettings

val appModules = listOf(
    ModuleCore(),
    ModuleCoreUi(),
    ModuleCoreNetworkClient(),
    ModuleCoreNetworkApi(),
    ModuleCoreDatabase(),
    ModuleFeatureMatchFeed(),
    ModuleFeatureSettings(),
).modules



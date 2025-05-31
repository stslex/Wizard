package com.stslex.wizard.feature.settings.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Action
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.Event
import com.stslex.wizard.feature.settings.ui.mvi.SettingsStore.State
import com.stslex.wizard.feature.settings.ui.mvi.handlers.SettingsComponent
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias SettingsStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the SettingsStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [com.stslex.wizard.feature.settings.ui.mvi.SettingsStore]
 * */
internal object SettingsFeature : Feature<SettingsStoreProcessor, SettingsComponent> {

    override val module: Module by lazy { ModuleFeatureSettings().module }

    private val scopeName = requireNotNull(SettingsScope::class.qualifiedName) {
        "Scope name is null. Please check the SettingsFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: SettingsComponent): SettingsStoreProcessor =
        rememberStoreProcessor(component)
}

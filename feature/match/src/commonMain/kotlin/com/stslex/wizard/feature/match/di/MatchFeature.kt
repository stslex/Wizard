package com.stslex.wizard.feature.match.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Action
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.Event
import com.stslex.wizard.feature.match.ui.mvi.MatchStore.State
import com.stslex.wizard.feature.match.ui.mvi.handlers.MatchComponent
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias MatchStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the ProfileStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [com.stslex.wizard.feature.match.ui.mvi.MatchStore]
 * */
internal object MatchFeature : Feature<MatchStoreProcessor, MatchComponent> {

    override val module: Module by lazy { ModuleFeatureMatch().module }

    private val scopeName = requireNotNull(MatchScope::class.qualifiedName) {
        "Scope name is null. Please check the MatchFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: MatchComponent): MatchStoreProcessor =
        rememberStoreProcessor(component)
}

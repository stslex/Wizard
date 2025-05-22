package com.stslex.wizard.feature.auth.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.auth.mvi.AuthStore.Action
import com.stslex.wizard.feature.auth.mvi.AuthStore.Event
import com.stslex.wizard.feature.auth.mvi.AuthStore.State
import com.stslex.wizard.feature.auth.mvi.handler.AuthComponent
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias AuthStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the ProfileStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [ProfileStore]
 * */
internal object AuthFeature : Feature<AuthStoreProcessor, AuthComponent> {

    override val module: Module by lazy { ModuleFeatureAuth().module }

    private val scopeName = requireNotNull(AuthScope::class.qualifiedName) {
        "Scope name is null. Please check the ProfileFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: AuthComponent): AuthStoreProcessor =
        rememberStoreProcessor(component)
}

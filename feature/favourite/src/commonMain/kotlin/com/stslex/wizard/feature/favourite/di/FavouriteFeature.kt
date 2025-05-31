package com.stslex.wizard.feature.favourite.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.Event
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore.State
import com.stslex.wizard.feature.favourite.mvi.handler.FavouriteComponent
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias FavouriteStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the FavouriteStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [com.stslex.wizard.feature.favourite.mvi.FavouriteStore]
 * */
internal object FavouriteFeature : Feature<FavouriteStoreProcessor, FavouriteComponent> {

    override val module: Module by lazy { ModuleFeatureFavourite().module }

    private val scopeName = requireNotNull(FavouriteScope::class.qualifiedName) {
        "Scope name is null. Please check the FavouriteFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: FavouriteComponent): FavouriteStoreProcessor =
        rememberStoreProcessor(component)
}

package com.stslex.wizard.feature.profile.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.profile.navigation.ProfileComponent
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Action
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.Event
import com.stslex.wizard.feature.profile.ui.store.ProfileStore.State
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias ProfileStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the ProfileStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [ProfileStore]
 * */
internal object ProfileFeature : Feature<ProfileStoreProcessor, ProfileComponent> {

    override val module: Module by lazy { ModuleFeatureProfile().module }

    private val scopeName = requireNotNull(ProfileScope::class.qualifiedName) {
        "Scope name is null. Please check the ProfileFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: ProfileComponent): ProfileStoreProcessor =
        rememberStoreProcessor(component)
}

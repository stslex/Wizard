package com.stslex.wizard.feature.match_feed.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.Event
import com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore.State
import com.stslex.wizard.feature.match_feed.ui.mvi.handlers.MatchDetailsComponent
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias MatchFeedStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the MatchFeedStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [com.stslex.wizard.feature.match_feed.ui.mvi.MatchFeedStore]
 * */
internal object MatchDetailsFeature : Feature<MatchFeedStoreProcessor, MatchDetailsComponent> {

    override val module: Module by lazy { ModuleFeatureMatchDetails().module }

    private val scopeName = requireNotNull(MatchDetailsScope::class.qualifiedName) {
        "Scope name is null. Please check the MatchFeedFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: MatchDetailsComponent): MatchFeedStoreProcessor =
        rememberStoreProcessor(component)
}

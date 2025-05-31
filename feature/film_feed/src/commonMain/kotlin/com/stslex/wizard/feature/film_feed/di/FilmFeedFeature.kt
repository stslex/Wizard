package com.stslex.wizard.feature.film_feed.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.Action
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.Event
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.State
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias FilmFeedStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the ProfileStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [ProfileStore]
 * */
internal object FilmFeedFeature : Feature<FilmFeedStoreProcessor, FilmFeedComponent> {

    override val module: Module by lazy { ModuleFeatureFilmFeed().module }

    private val scopeName = requireNotNull(FilmFeedScope::class.qualifiedName) {
        "Scope name is null. Please check the ProfileFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: FilmFeedComponent): FilmFeedStoreProcessor =
        rememberStoreProcessor(component)
}

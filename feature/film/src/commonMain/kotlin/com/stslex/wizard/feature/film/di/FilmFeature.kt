package com.stslex.wizard.feature.film.di

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.Feature
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import com.stslex.wizard.core.ui.mvi.v2.processor.rememberStoreProcessor
import com.stslex.wizard.feature.film.mvi.FilmStore.Action
import com.stslex.wizard.feature.film.mvi.FilmStore.Event
import com.stslex.wizard.feature.film.mvi.FilmStore.State
import com.stslex.wizard.feature.film.mvi.handlers.FilmComponent
import org.koin.core.module.Module
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.ksp.generated.module

internal typealias FilmStoreProcessor = StoreProcessor<State, Action, Event>

/**
 * ProfileFeature is a Koin feature module that provides the FavouriteStore processor.
 * It is responsible for managing the state and actions related to the profile feature.
 *
 * @see [com.stslex.wizard.feature.favourite.mvi.FavouriteStore]
 * */
internal object FilmFeature : Feature<FilmStoreProcessor, FilmComponent> {

    override val module: Module by lazy { ModuleFeatureFilm().module }

    private val scopeName = requireNotNull(FilmScope::class.qualifiedName) {
        "Scope name is null. Please check the FavouriteFeature class."
    }

    override val scope: Scope by lazy {
        getKoin().getOrCreateScope(
            scopeId = scopeName,
            qualifier = qualifier(scopeName)
        )
    }

    @Composable
    override fun processor(component: FilmComponent): FilmStoreProcessor =
        rememberStoreProcessor(component)
}

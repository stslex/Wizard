package com.stslex.wizard.core.ui.mvi.v2

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import org.koin.core.component.KoinScopeComponent
import org.koin.core.module.Module

/**
 * Feature is a Koin feature module that provides a StoreProcessor.
 * It is responsible for managing the state and actions related to the feature.
 *
 * @see [StoreProcessor]
 * */
interface Feature<TProcessor : StoreProcessor<*, *, *>, TComponent : Component> :
    KoinScopeComponent {

    val module: Module

    @Composable
    fun processor(component: TComponent): TProcessor
}
package com.stslex.wizard.core.ui.mvi.v2

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.navigation.Component
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Suppress("UNCHECKED_CAST")
@Composable
fun <TProcessor : StoreProcessor<*, *, *>, TComponent : Component> NavComponentScreen(
    feature: Feature<TProcessor, TComponent>,
    component: TComponent,
    content: @Composable (TProcessor) -> Unit
) {
    rememberKoinModules(unloadModules = true) {
        listOf(feature.module)
    }
    content(feature.processor(component))
}

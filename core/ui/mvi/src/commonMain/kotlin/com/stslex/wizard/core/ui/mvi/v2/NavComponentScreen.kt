package com.stslex.wizard.core.ui.mvi.v2

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.v2.processor.StoreProcessor
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Suppress("UNCHECKED_CAST")
inline fun <reified TScreen : Screen, TProcessor : StoreProcessor<*, *, *>> NavGraphBuilder.navComponentScreen(
    feature: Feature<TProcessor>,
    noinline content: @Composable (TScreen, TProcessor) -> Unit
) {
    navScreen<TScreen> { screen ->
        rememberKoinModules(unloadModules = true) { listOf(feature.module) }
        content(screen, feature.processor())
    }
}

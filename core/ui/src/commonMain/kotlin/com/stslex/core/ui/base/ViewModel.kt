package com.stslex.core.ui.base

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.getKoin
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module

expect open class ViewModel() {

    val scope: CoroutineScope
}

expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>

@Composable
expect inline fun <reified T : ViewModel> getViewModel(): T

@Composable
inline fun <reified VM : ViewModel> rememberStore(): VM {
    val navigator = LocalNavigator.currentOrThrow
    val module = module { factory { navigator } }
    getKoin().loadModules(
        modules = listOf(module),
        allowOverride = true,
    )
    return getViewModel()
}

package com.stslex.wizard.core.ui.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.compose.getKoin
import org.koin.compose.koinInject
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

@Composable
fun setupNavigator() {
    val navigator = LocalNavigator.currentOrThrow
    val koin = getKoin()
    LaunchedEffect(navigator.hashCode()) {
        koin.get<AppNavigator>().setNavigator(navigator)
    }
}

expect inline fun <reified T : ViewModel> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T>

inline fun <reified T : Store<*, *, *, *>> Module.storeDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<T> = viewModelDefinition(qualifier, definition)

@Composable
inline fun <reified T : Store<*, *, *, *>> getStore(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = koinInject(
    qualifier = qualifier,
    parameters = parameters
)

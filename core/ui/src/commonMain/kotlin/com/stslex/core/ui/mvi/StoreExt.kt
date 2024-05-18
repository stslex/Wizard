package com.stslex.core.ui.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.stslex.core.ui.navigation.AppNavigator
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

expect inline fun <reified S : Store<*, *, *>, reified T : BaseStore<*, *, *, *>> Module.viewModelDefinition(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): KoinDefinition<S>

@Composable
inline fun <reified S : Store<*, *, *>> getStore(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): S = koinInject(
    qualifier = qualifier,
    parameters = parameters
)

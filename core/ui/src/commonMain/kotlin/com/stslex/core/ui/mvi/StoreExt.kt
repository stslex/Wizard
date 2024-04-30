package com.stslex.core.ui.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.stslex.core.ui.navigation.AppNavigator
import org.koin.compose.getKoin
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

@Composable
inline fun <T : Store<*, *, *>> Screen.getStore(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T {
    val koin = getKoin()
    return rememberScreenModel(tag = qualifier?.value) { koin.get(qualifier, parameters) }
}
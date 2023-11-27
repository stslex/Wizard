package com.stslex.core.ui.base

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.stslex.core.ui.navigation.AppNavigator
import kotlinx.coroutines.CoroutineScope
import org.koin.compose.getKoin
import org.koin.core.definition.Definition
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.qualifier.Qualifier

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
inline fun <reified S : ScreenModel> Screen.getScreenStore(): S {
    val navigator = LocalNavigator.currentOrThrow
    getKoin().get<AppNavigator>().setNavigator(navigator)
    return getScreenModel<S>()
}

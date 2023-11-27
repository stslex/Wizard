package com.stslex.core.ui.mvi

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.stslex.core.ui.navigation.AppNavigator
import org.koin.compose.getKoin

@Composable
inline fun <reified S : ScreenModel> Screen.getScreenStore(): S {
    val navigator = LocalNavigator.currentOrThrow
    getKoin().get<AppNavigator>().setNavigator(navigator)
    return getScreenModel<S>()
}
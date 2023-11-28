package com.stslex.core.ui.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.stslex.core.ui.navigation.AppNavigator
import org.koin.compose.getKoin

@Composable
fun setupNavigator() {
    val navigator = LocalNavigator.currentOrThrow
    val koin = getKoin()
    LaunchedEffect(navigator.hashCode()) {
        koin.get<AppNavigator>().setNavigator(navigator)
    }
}
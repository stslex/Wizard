package com.stslex.wizard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.stslex.core.network.utils.token.AuthController
import com.stslex.core.ui.navigation.AppNavigator
import com.stslex.core.ui.navigation.AppScreen
import com.stslex.feature.auth.ui.AuthScreen
import com.stslex.wizard.main_screen.MainScreen
import org.koin.compose.getKoin

@Composable
fun InitialApp(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val userStore = remember {
        koin.get<AuthController>()
    }
    val navigator = remember {
        koin.get<AppNavigator>()
    }

    LaunchedEffect(Unit) {
        userStore.isAuthFlow.collect { isAuth ->
            val currentScreen = navigator.currentScreen
            if (
                isAuth.not() &&
                currentScreen != null &&
                currentScreen != AppScreen.Auth
            ) {
                navigator.navigate(AppScreen.Auth)
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { _ ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Navigator(
                screen = if (userStore.isAuth) MainScreen else AuthScreen
            ) {
                SlideTransition(it)
            }
        }
    }
}

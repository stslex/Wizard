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
import androidx.navigation.NavHostController
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.network.utils.token.AuthController
import com.stslex.wizard.host.AppNavigationHost
import org.koin.compose.getKoin

@Composable
fun InitialApp(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val userStore = remember {
        koin.get<AuthController>()
    }

    LaunchedEffect(Unit) {
        userStore.isAuthFlow.collect { isAuth ->
            val currentScreen = navHostController.currentDestination
            if (
                isAuth.not() &&
                currentScreen != null &&
                currentScreen.route != Screen.Auth.serializer().toString()
            ) {
                navHostController.navigate(Screen.Auth)
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
            AppNavigationHost(
                navHostController = navHostController,
                startScreen = if (userStore.isAuth) Screen.Auth else Screen.Main
            )
        }
    }
}

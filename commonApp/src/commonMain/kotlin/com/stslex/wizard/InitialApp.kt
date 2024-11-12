package com.stslex.wizard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.stslex.wizard.bottom_bar.BottomAppBarResource
import com.stslex.wizard.bottom_bar.BottomAppBarResource.Companion.getByRoute
import com.stslex.wizard.bottom_bar.MainBottomAppBar
import com.stslex.wizard.core.core.Logger
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
    var currentDestination by remember {
        mutableStateOf<Screen?>(null)
    }

    navHostController.addOnDestinationChangedListener { _, destination, _ ->
        Logger.d("current route: ${destination.route}")
        currentDestination = destination.route?.let(::getByRoute)
        Logger.d("currentDestination: $currentDestination")
    }

    LaunchedEffect(Unit) {
        userStore.isAuthFlow.collect { isAuth ->
            val currentScreen = navHostController.currentDestination
            val authScreen = Screen.Auth::class.simpleName.orEmpty()
            if (
                isAuth.not() &&
                currentScreen != null &&
                currentScreen.route?.contains(authScreen) == true
            ) {
                navHostController.navigate(Screen.Auth)
            }
        }
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {
            AnimatedVisibility(
                visible = BottomAppBarResource.isAppbar(currentDestination),
                enter = slideInVertically(tween(300)) { it },
                exit = slideOutVertically(tween(300)) { it }
            ) {
                MainBottomAppBar(
                    onBottomAppBarClick = { navHostController.navigate(it) },
                    currentDestination = currentDestination
                )
            }
        }
    ) { _ ->
        Box(modifier = Modifier.fillMaxSize()) {
            AppNavigationHost(
                navHostController = navHostController,
                startScreen = if (userStore.isAuth) Screen.FilmFeed else Screen.Auth
            )
        }
    }
}

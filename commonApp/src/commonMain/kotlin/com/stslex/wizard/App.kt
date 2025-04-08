package com.stslex.wizard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.stslex.wizard.bottom_bar.MainBottomAppBar
import com.stslex.wizard.core.navigation.Config
import com.stslex.wizard.core.ui.kit.theme.AppTheme
import com.stslex.wizard.host.AppNavigationHost
import com.stslex.wizard.host.RootComponent

@Composable
fun App(rootComponent: RootComponent) {
    AppTheme {
        var currentDestination by remember {
            mutableStateOf<Config>(rootComponent.stack.value.active.configuration)
        }

        DisposableEffect(rootComponent) {
            val cancellable = rootComponent.onConfigChanged { currentDestination = it }
            onDispose {
                cancellable.cancel()
            }
        }

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            bottomBar = {
                AnimatedVisibility(
                    visible = currentDestination is Config.BottomBar,
                    enter = slideInVertically(tween(300)) { it },
                    exit = slideOutVertically(tween(300)) { it }
                ) {
                    MainBottomAppBar(
                        onBottomAppBarClick = {
                            rootComponent.onBottomAppBarClick(it)
                        },
                        currentDestination = currentDestination
                    )
                }
            }
        ) { paddingValues ->
            AppNavigationHost(
                modifier = Modifier.padding(
                    bottom = paddingValues.calculateBottomPadding()
                ),
                rootComponent = rootComponent
            )
        }
    }
}
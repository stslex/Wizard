package com.stslex.wizard.host

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.feature.auth.navigation.authGraph

@Composable
fun AppNavigationHost(
    navHostController: NavHostController,
    startScreen: Screen,
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        NavHost(
            navController = navHostController,
            startDestination = startScreen
        ) {
            authGraph()

        }
    }
}
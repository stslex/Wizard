package com.stslex.wizard.config

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import org.koin.core.KoinApplication

@Composable
fun KoinApp(
    additionalSetup: KoinApplication.() -> Unit = {},
    content: @Composable (navHostController: NavHostController) -> Unit
) {

}

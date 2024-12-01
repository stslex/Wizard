package com.stslex.wizard

import androidx.compose.runtime.Composable
import com.stslex.wizard.config.KoinApp
import com.stslex.wizard.core.ui.kit.theme.AppTheme
import org.koin.core.KoinApplication

@Composable
fun App(
    additionalSetup: KoinApplication.() -> Unit = {},
) {
    KoinApp(additionalSetup) { controller ->
        AppTheme {
            InitialApp(controller)
        }
    }
}
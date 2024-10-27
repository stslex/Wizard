package com.stslex.wizard.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.stslex.core.network.utils.AppKamelConfig
import io.kamel.image.config.LocalKamelConfig

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isDarkTheme) {
        darkColorScheme()
    } else {
        lightColorScheme()
    }
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
//    CompositionLocalProvider(
//        LocalKamelConfig provides AppKamelConfig.KamelLoggingConfig
//    ) {
//
//    }
}
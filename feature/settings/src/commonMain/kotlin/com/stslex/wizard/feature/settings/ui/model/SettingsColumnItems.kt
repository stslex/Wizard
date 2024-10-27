package com.stslex.wizard.feature.settings.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.ui.graphics.vector.ImageVector

enum class SettingsColumnItems(
    val title: String,
    val icon: ImageVector
) {
    LOG_OUT(
        title = "Log out",
        icon = Icons.AutoMirrored.Filled.ExitToApp
    )
}
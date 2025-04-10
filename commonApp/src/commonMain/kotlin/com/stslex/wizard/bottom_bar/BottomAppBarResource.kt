package com.stslex.wizard.bottom_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.stslex.wizard.core.navigation.Config

enum class BottomAppBarResource(
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val title: String,
    val config: Config.BottomBar
) {
    FILM_FEED(
        unselectedIcon = Icons.AutoMirrored.Outlined.List,
        selectedIcon = Icons.AutoMirrored.Filled.List,
        title = "films",
        config = Config.BottomBar.FilmFeed
    ),
    MATCHES(
        unselectedIcon = Icons.Outlined.PlayArrow,
        selectedIcon = Icons.Default.PlayArrow,
        title = "matches",
        config = Config.BottomBar.Match(Config.BottomBar.Match.Type.SELF)
    ),
    PROFILE(
        unselectedIcon = Icons.Outlined.AccountBox,
        selectedIcon = Icons.Default.AccountBox,
        title = "profile",
        config = Config.BottomBar.Profile(Config.BottomBar.Profile.Type.SELF)
    );

    fun getIcon(isSelected: Boolean) = if (isSelected) selectedIcon else unselectedIcon
}
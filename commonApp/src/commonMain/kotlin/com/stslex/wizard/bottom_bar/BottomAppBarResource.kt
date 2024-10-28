package com.stslex.wizard.bottom_bar;

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.stslex.wizard.core.navigation.Screen
import kotlin.reflect.KClass

enum class BottomAppBarResource(
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val title: String,
    val screen: Screen
) {
    FILM_FEED(
        unselectedIcon = Icons.AutoMirrored.Outlined.List,
        selectedIcon = Icons.AutoMirrored.Filled.List,
        title = "films",
        screen = Screen.FilmFeed
    ),
    MATCHES(
        unselectedIcon = Icons.Outlined.PlayArrow,
        selectedIcon = Icons.Default.PlayArrow,
        title = "matches",
        screen = Screen.Match(Screen.Match.Type.SELF)
    ),
    PROFILE(
        unselectedIcon = Icons.Outlined.AccountBox,
        selectedIcon = Icons.Default.AccountBox,
        title = "profile",
        screen = Screen.Profile(Screen.Profile.Type.SELF)
    );

    fun getIcon(isSelected: Boolean) = if (isSelected) selectedIcon else unselectedIcon

    companion object {

        fun isAppbar(screen: Any?): Boolean = entries.any { it.screen == screen }

        fun getByRoute(route: String): Screen? = when {
            Screen.FilmFeed::class.checkScreen(route) -> FILM_FEED
            Screen.Match::class.checkScreen(route) -> MATCHES
            Screen.Profile::class.checkScreen(route) -> PROFILE
            else -> null
        }?.screen

        private fun <T : Screen> KClass<T>.checkScreen(route: String): Boolean =
            route.contains(simpleName.orEmpty())
    }
}
package main_screen.bottom_nav_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator

@Composable
fun BottomNavigationBar(
    tabNavigator: TabNavigator,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        BottomNavigationTabs.entries.forEach { tab ->
            TabNavigationItem(
                tab = tab.tab,
                tabNavigator = tabNavigator
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(
    tab: Tab,
    tabNavigator: TabNavigator,
    modifier: Modifier = Modifier
) {
    NavigationBarItem(
        modifier = modifier,
        selected = tabNavigator.current == tab,
        onClick = {
            tabNavigator.current = tab
        },
        label = { Text(tab.options.title) },
        alwaysShowLabel = false,
        icon = {
            Icon(
                painter = checkNotNull(tab.options.icon),
                contentDescription = tab.options.title
            )
        }
    )
}
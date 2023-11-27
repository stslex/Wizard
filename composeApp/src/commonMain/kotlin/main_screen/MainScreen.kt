package main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import main_screen.bottom_nav_bar.BottomNavigationBar
import main_screen.bottom_nav_bar.FeedTab

object MainScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(
            tab = FeedTab,
        ) { tabNavigator ->
            Scaffold(
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    BottomNavigationBar(tabNavigator)
                }
            )
        }
    }
}

package com.stslex.wizard.main_screen.bottom_nav_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.stslex.wizard.feature.film_feed.ui.FeedScreen

object FeedTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "feed"
            val icon = rememberVectorPainter(Icons.AutoMirrored.Filled.List)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(FeedScreen) {
            SlideTransition(it)
        }
    }
}
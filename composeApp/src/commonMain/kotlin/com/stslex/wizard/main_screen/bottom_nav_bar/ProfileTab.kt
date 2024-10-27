package com.stslex.wizard.main_screen.bottom_nav_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.stslex.wizard.feature.profile.navigation.ProfileScreenArguments
import com.stslex.feature.profile.ui.ProfileScreen

object ProfileTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "profile"
            val icon = rememberVectorPainter(Icons.Default.AccountBox)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(
            ProfileScreen(args = ProfileScreenArguments.Self)
        )
    }
}

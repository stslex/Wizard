package com.stslex.feature.feed.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.base.rememberStore
import com.stslex.feature.feed.ui.store.FeedScreenStore

object FeedScreen : Screen {

    @Composable
    override fun Content() {
        FeedScreen()
    }
}

@Composable
fun FeedScreen() {
    val store = rememberStore<FeedScreenStore>()
    val state by remember { store.state }.collectAsState()

    Text("Feed screen")
}

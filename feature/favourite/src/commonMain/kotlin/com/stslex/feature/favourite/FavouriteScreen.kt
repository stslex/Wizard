package com.stslex.feature.favourite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.mvi.getStoreTest
import com.stslex.feature.favourite.ui.components.FavouriteScreen
import com.stslex.feature.favourite.ui.store.FavouriteStore
import com.stslex.feature.favourite.ui.store.FavouriteStore.Action

data class FavouriteScreen(
    val uuid: String
) : Screen {

    @Composable
    override fun Content() {
        val store = getStoreTest<FavouriteStore>()
        val state by remember { store.state }.collectAsState()

        LaunchedEffect(key1 = Unit) {
            store.sendAction(Action.Init(uuid = uuid))
        }

        FavouriteScreen(
            state = state,
            onAction = store::sendAction
        )
    }
}

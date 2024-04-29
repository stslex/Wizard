package com.stslex.feature.favourite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.stslex.feature.favourite.ui.components.FavouriteScreen
import com.stslex.feature.favourite.ui.store.FavouriteStore.Action
import com.stslex.feature.favourite.ui.store.FavouriteStoreImpl

data class FavouriteScreen(
    val uuid: String
) : Screen {

    @Composable
    override fun Content() {
        val store = getScreenModel<FavouriteStoreImpl>()
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

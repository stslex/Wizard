package com.stslex.wizard.feature.favourite.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.kit.mvi.getStore
import com.stslex.wizard.feature.favourite.ui.FavouriteScreen
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Action

fun NavGraphBuilder.graphFavourite() {
    navScreen<Screen.Favourite> { screen ->
        val store = getStore<FavouriteStore>()
        val state by remember { store.state }.collectAsState()

        LaunchedEffect(key1 = Unit) {
            store.sendAction(Action.Init(uuid = screen.uuid))
        }

        FavouriteScreen(
            state = state,
            onAction = store::sendAction
        )
    }
}
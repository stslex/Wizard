package com.stslex.wizard.feature.favourite.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.favourite.navigation.FavouriteComponent
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStore.Action
import com.stslex.wizard.feature.favourite.ui.store.FavouriteStoreImpl
import org.koin.core.parameter.parametersOf

@Composable
fun FavouriteScreen(component: FavouriteComponent) {
    val store = getStore<FavouriteStore, FavouriteStoreImpl>(
        parameters = { parametersOf(component) }
    )
    val state by remember { store.state }.collectAsState()

    LaunchedEffect(key1 = Unit) { store.consume(Action.Init) }

    FavouriteScreenWidget(
        state = state,
        onAction = store::consume
    )
}
package com.stslex.wizard.feature.film_feed.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.film_feed.ui.FeedScreen
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event

fun NavGraphBuilder.graphFilmFeed() {
    navScreen<Screen.FilmFeed> { screen ->
        val store = getStore<FeedStore>()
        val state by remember { store.state }.collectAsState()
        LaunchedEffect(Unit) {
            store.event.collect { event ->
                when (event) {
                    is Event.ErrorSnackBar -> {
                        // TODO show error snackbar
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            store.sendAction(Action.LoadFilms)
        }
        FeedScreen(
            state = state,
            sendAction = store::sendAction
        )
    }
}
package com.stslex.wizard.feature.match_feed.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.ui.mvi.navComponentScreen
import com.stslex.wizard.feature.match_feed.ui.MatchFeedScreen
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.Event.ErrorSnackBar
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreImpl

fun NavGraphBuilder.graphMatchFeed() {
    navComponentScreen<Screen.MatchFeed, MatchFeedStore, MatchFeedStoreImpl> { _, store ->
        val state by remember { store.state }.collectAsState()

        LaunchedEffect(Unit) {
            store.event.collect { event ->
                when (event) {
                    is ErrorSnackBar -> {
                        // TODO show error snackbar
                    }
                }
            }
        }
        LaunchedEffect(Unit) {
            store.consume(Action.Init)
        }
        MatchFeedScreen(
            state = state,
            sendAction = store::consume
        )
    }
}
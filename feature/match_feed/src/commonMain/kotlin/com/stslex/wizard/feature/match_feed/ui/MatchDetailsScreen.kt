package com.stslex.wizard.feature.match_feed.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.match_feed.navigation.MatchDetailsComponent
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.Action
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStore.Event.ErrorSnackBar
import com.stslex.wizard.feature.match_feed.ui.store.MatchFeedStoreImpl
import org.koin.core.parameter.parametersOf

@Composable
fun MatchDetailsScreen(
    component: MatchDetailsComponent,
    uuid: String
) {
    val store = getStore<MatchFeedStore, MatchFeedStoreImpl>(
        parameters = { parametersOf(component) }
    )
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
    MatchDetailsScreenWidget(
        state = state,
        sendAction = store::consume
    )
}

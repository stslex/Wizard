package com.stslex.feature.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.stslex.feature.favourite.ui.store.FavouriteScreenState
import com.stslex.feature.favourite.ui.store.FavouriteStore
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.Action
import com.stslex.feature.favourite.ui.store.FavouriteStoreComponent.State

data class FavouriteScreen(
    val uuid: String
) : Screen {

    @Composable
    override fun Content() {
        val store = getScreenModel<FavouriteStore>()
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

@Composable
internal fun FavouriteScreen(
    state: State,
    onAction: (Action) -> Unit
) {
    when (state.screen) {
        is FavouriteScreenState.Content -> {
            LazyColumn {
                items(state.data.size) { index ->
                    Text(
                        "test: ${state.data[index].title}"
                    )
                }
            }
            if (state.screen is FavouriteScreenState.Content.Loading) {
                Text("Loading")
            }
        }

        is FavouriteScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${state.screen.error.message}")
            }
        }

        FavouriteScreenState.Shimmer -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Shimmer")
            }
        }

        FavouriteScreenState.Empty -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Empty")
            }
        }
    }
}
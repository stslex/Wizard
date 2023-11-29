package com.stslex.feature.film.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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
import com.stslex.core.ui.mvi.setupNavigator
import com.stslex.feature.film.ui.components.FilmContentScreen
import com.stslex.feature.film.ui.store.FilmScreenState
import com.stslex.feature.film.ui.store.FilmStore
import com.stslex.feature.film.ui.store.FilmStoreComponent.Action
import com.stslex.feature.film.ui.store.FilmStoreComponent.State

data class FilmScreen(
    private val id: String
) : Screen {

    @Composable
    override fun Content() {
        setupNavigator()

        val store = getScreenModel<FilmStore>()
        LaunchedEffect(Unit) {
            store.sendAction(Action.Init(id))
        }
        val state by remember { store.state }.collectAsState()
        LaunchedEffect(Unit) {
            store.event.collect { event ->
                when (event) {
                    else -> Unit
                }
            }
        }
        FilmContent(
            state = state,
            onAction = store::sendAction
        )
    }
}

@Composable
internal fun FilmContent(
    modifier: Modifier = Modifier,
    state: State,
    onAction: (Action) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (
            val screenState = state.screenState
        ) {
            FilmScreenState.Loading -> FilmLoading()

            is FilmScreenState.Content -> FilmContentScreen(
                film = screenState.data,
                onLikeClick = {
                    onAction(Action.LikeButtonClick)
                },
                onBackClick = {
                    onAction(Action.BackButtonClick)
                }
            )
        }
    }
}

@Composable
internal fun FilmLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Loading...")
            CircularProgressIndicator()
        }
    }
}
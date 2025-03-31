package com.stslex.wizard.feature.film.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.film.ui.components.FilmContentScreen
import com.stslex.wizard.feature.film.ui.store.FilmScreenState
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action
import com.stslex.wizard.feature.film.ui.store.FilmStore.State

@Composable
internal fun FilmScreen(
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
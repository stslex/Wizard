package com.stslex.wizard.feature.film.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.stslex.wizard.feature.film.mvi.FilmScreenState
import com.stslex.wizard.feature.film.mvi.FilmStore.Action
import com.stslex.wizard.feature.film.mvi.FilmStore.State
import com.stslex.wizard.feature.film.ui.components.FilmContentScreen

@Composable
internal fun FilmScreenWidget(
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
                    onAction(Action.Click.LikeButtonClick)
                },
                onBackClick = {
                    onAction(Action.Click.BackButtonClick)
                }
            )
        }
    }
}

@Composable
private fun FilmLoading(
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
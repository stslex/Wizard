package com.stslex.feature.film.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.base.image.NetworkImage
import com.stslex.core.ui.base.onClickDelay
import com.stslex.core.ui.mvi.getScreenStore
import com.stslex.core.ui.theme.AppDimension
import com.stslex.feature.film.ui.model.Film
import com.stslex.feature.film.ui.store.FilmScreenState
import com.stslex.feature.film.ui.store.FilmStore
import com.stslex.feature.film.ui.store.FilmStoreComponent.Action
import com.stslex.feature.film.ui.store.FilmStoreComponent.State

data class FilmScreen(
    private val id: String
) : Screen {

    @Composable
    override fun Content() {
        val store = getScreenStore<FilmStore>()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FilmContent(
    modifier: Modifier = Modifier,
    state: State,
    onAction: (Action) -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Film")
                },
                navigationIcon = {
                    IconButton(
                        onClick = onClickDelay { onAction(Action.BackButtonClick) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                        )
                    }

                }
            )
        }
    ) {
        when (val screenState = state.screenState) {
            FilmScreenState.Loading -> FilmLoading()
            is FilmScreenState.Content -> FilmSuccess(
                film = screenState.data,
                onAction = onAction
            )
        }
    }
}

@Composable
internal fun FilmSuccess(
    film: Film,
    onAction: (Action) -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
    ) {
        val defaultHeight = maxHeight / 2
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            NetworkImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(defaultHeight),
                url = film.poster,
                contentDescription = film.title,
                contentScale = ContentScale.FillWidth,
            )
            Spacer(modifier = Modifier.height(AppDimension.Padding.medium))
            Text(
                text = film.title,
                style = MaterialTheme.typography.displayMedium,
            )
            Spacer(modifier = Modifier.height(AppDimension.Padding.large))
            Text(
                text = film.description,
                style = MaterialTheme.typography.bodyMedium,
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
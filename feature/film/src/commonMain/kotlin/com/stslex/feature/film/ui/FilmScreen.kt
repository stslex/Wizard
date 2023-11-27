package com.stslex.feature.film.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import cafe.adriel.voyager.core.screen.Screen
import com.stslex.core.ui.base.rememberStore
import com.stslex.feature.film.ui.store.FilmScreenState
import com.stslex.feature.film.ui.store.FilmStore
import com.stslex.feature.film.ui.store.FilmStoreComponent

data class FilmScreen(private val id: String) : Screen {

    @Composable
    override fun Content() {
        val store = rememberStore<FilmStore>()
        store.sendAction(FilmStoreComponent.Action.Init(id))

        val state by remember { store.state }.collectAsState()

        when (val screenState = state.screenState) {
            FilmScreenState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Text(text = "Loading...")
                        CircularProgressIndicator()
                    }
                }
            }

            is FilmScreenState.Success -> {
                Text(text = screenState.data.title)
            }
        }
    }
}
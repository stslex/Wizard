package com.stslex.wizard.feature.film.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.core.ui.mvi.store_di.getStore
import com.stslex.wizard.feature.film.navigation.FilmComponent
import com.stslex.wizard.feature.film.ui.store.FilmStore
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action
import com.stslex.wizard.feature.film.ui.store.FilmStoreImpl
import org.koin.core.parameter.parametersOf

@Composable
fun FilmScreen(
    filmId: String,
    component: FilmComponent,
) {
    val store = getStore<FilmStore, FilmStoreImpl>(
        parameters = { parametersOf(component) }
    )

    LaunchedEffect(Unit) {
        store.consume(Action.Init(filmId))
    }
    val state by remember { store.state }.collectAsState()
    LaunchedEffect(Unit) {
        store.event.collect { event ->
            when (event) {
                else -> Unit
            }
        }
    }
    FilmScreenWidget(
        state = state,
        onAction = store::consume
    )
}
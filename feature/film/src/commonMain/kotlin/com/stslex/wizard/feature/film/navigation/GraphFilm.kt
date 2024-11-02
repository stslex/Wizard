package com.stslex.wizard.feature.film.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.stslex.wizard.core.navigation.Screen
import com.stslex.wizard.core.navigation.navScreen
import com.stslex.wizard.core.ui.mvi.getStore
import com.stslex.wizard.feature.film.ui.FilmScreen
import com.stslex.wizard.feature.film.ui.store.FilmStore
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action

fun NavGraphBuilder.graphFilm() {
    navScreen<Screen.Film> { screen ->
        val store = getStore<FilmStore>()
        LaunchedEffect(Unit) {
            store.sendAction(Action.Init(screen.id))
        }
        val state by remember { store.state }.collectAsState()
        LaunchedEffect(Unit) {
            store.event.collect { event ->
                when (event) {
                    else -> Unit
                }
            }
        }
        FilmScreen(
            state = state,
            onAction = store::sendAction
        )
    }
}
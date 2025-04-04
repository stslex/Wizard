package com.stslex.wizard.feature.film.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.feature.film.di.ModuleFeatureFilm
import com.stslex.wizard.feature.film.navigation.FilmComponent
import com.stslex.wizard.feature.film.ui.store.FilmStore.Action
import com.stslex.wizard.feature.film.ui.store.FilmStoreImpl
import org.koin.compose.module.rememberKoinModules
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun FilmScreen(
    filmId: String,
    component: FilmComponent,
) {
    rememberKoinModules(unloadModules = true) {
        listOf(ModuleFeatureFilm().module)
    }
    val store = koinViewModel<FilmStoreImpl>(
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
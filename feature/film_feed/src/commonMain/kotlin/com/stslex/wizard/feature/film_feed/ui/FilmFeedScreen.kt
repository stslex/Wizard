package com.stslex.wizard.feature.film_feed.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.stslex.wizard.feature.film_feed.di.ModuleFeatureFilmFeed
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Action
import com.stslex.wizard.feature.film_feed.ui.store.FeedStore.Event
import com.stslex.wizard.feature.film_feed.ui.store.FeedStoreImpl
import org.koin.compose.module.rememberKoinModules
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun FilmFeedScreen(
    component: FilmFeedComponent,
) {
    rememberKoinModules(unloadModules = true) {
        listOf(ModuleFeatureFilmFeed().module)
    }
    val store = koinViewModel<FeedStoreImpl>(
        parameters = { parametersOf(component) }
    )

    val state by remember { store.state }.collectAsState()
    LaunchedEffect(Unit) {
        store.consume(Action.LoadFilms)
        store.event.collect { event ->
            when (event) {
                is Event.ErrorSnackBar -> {
                    // TODO show error snackbar
                }
            }
        }
    }
    FilmFeedWidget(
        state = state,
        consume = store::consume
    )
}
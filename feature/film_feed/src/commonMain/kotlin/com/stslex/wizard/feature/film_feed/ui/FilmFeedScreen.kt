package com.stslex.wizard.feature.film_feed.ui

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.film_feed.di.FilmFeedFeature
import com.stslex.wizard.feature.film_feed.navigation.FilmFeedComponent
import com.stslex.wizard.feature.film_feed.mvi.FeedStore.Event

@Composable
fun FilmFeedScreen(component: FilmFeedComponent) {
    NavComponentScreen(FilmFeedFeature, component) { processor ->
        processor.handle { event ->
            when (event) {
                is Event.ErrorSnackBar -> {
                    // TODO show error snackbar
                }
            }
        }
        FilmFeedWidget(
            state = processor.state,
            consume = processor::consume
        )
    }
}
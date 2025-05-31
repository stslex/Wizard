package com.stslex.wizard.feature.film.ui

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.film.di.FilmFeature
import com.stslex.wizard.feature.film.mvi.handlers.FilmComponent

@Composable
fun FilmScreen(component: FilmComponent) {
    NavComponentScreen(FilmFeature, component) { processor ->
        FilmScreenWidget(
            state = processor.state.value,
            onAction = processor::consume
        )
    }
}
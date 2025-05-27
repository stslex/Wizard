package com.stslex.wizard.feature.favourite.ui

import androidx.compose.runtime.Composable
import com.stslex.wizard.core.ui.mvi.v2.NavComponentScreen
import com.stslex.wizard.feature.favourite.di.FavouriteFeature
import com.stslex.wizard.feature.favourite.mvi.FavouriteStore
import com.stslex.wizard.feature.favourite.mvi.handler.FavouriteComponent

@Composable
fun FavouriteScreen(component: FavouriteComponent) {
    NavComponentScreen(FavouriteFeature, component) { processor ->
        processor.handle {
            when (it) {
                is FavouriteStore.Event.ShowSnackbar -> Unit // do nothing
            }
        }
        FavouriteScreenWidget(
            state = processor.state.value,
            onAction = processor::consume
        )
    }
}
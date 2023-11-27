package com.stslex.feature.film.ui.store

import androidx.compose.runtime.Stable
import com.stslex.feature.film.ui.model.Film

@Stable
sealed interface FilmScreenState {

    @Stable
    data class Content(val data: Film) : FilmScreenState

    data object Loading : FilmScreenState
}
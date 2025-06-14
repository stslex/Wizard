package com.stslex.wizard.feature.film.mvi

import androidx.compose.runtime.Stable
import com.stslex.wizard.feature.film.ui.model.Film

@Stable
sealed interface FilmScreenState {

    @Stable
    data class Content(val data: Film) : FilmScreenState

    data object Loading : FilmScreenState

    val result: Film?
        get() = (this as? Content)?.data
}
package com.stslex.wizard.feature.film.domain.interactor

import com.stslex.wizard.feature.film.domain.model.FilmDomain
import kotlinx.coroutines.flow.Flow

internal interface FilmInteractor {

    fun getFilm(id: String): Flow<FilmDomain>

    suspend fun likeFilm(film: FilmDomain)

    suspend fun dislikeFilm(id: String)
}
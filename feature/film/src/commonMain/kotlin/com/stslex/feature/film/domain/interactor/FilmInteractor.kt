package com.stslex.feature.film.domain.interactor

import com.stslex.feature.film.domain.model.FilmDomain
import kotlinx.coroutines.flow.Flow

interface FilmInteractor {

    fun getFilm(id: String): Flow<FilmDomain>
}
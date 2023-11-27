package com.stslex.feature.film.data.repository

import com.stslex.feature.film.data.model.FilmData
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getFilm(id: String): Flow<FilmData>
}
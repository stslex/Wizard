package com.stslex.wizard.feature.film.data.repository

import com.stslex.wizard.feature.film.data.model.FilmData
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getFilm(id: String): Flow<FilmData>

    suspend fun likeFilm(filmData: FilmData)

    suspend fun dislikeFilm(id: String)
}
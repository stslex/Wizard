package com.stslex.feature.film.data.repository

import com.stslex.feature.film.data.model.FilmData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FilmRepositoryImpl : FilmRepository {
    override fun getFilm(id: String): Flow<FilmData> = flow {
        delay(3000)
        TODO("Not yet implemented")
    }
}
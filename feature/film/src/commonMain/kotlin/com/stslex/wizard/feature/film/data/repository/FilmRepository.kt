package com.stslex.wizard.feature.film.data.repository

import com.stslex.wizard.feature.film.data.model.FilmData
import com.stslex.wizard.feature.film.di.FilmScope
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Scope
import org.koin.core.annotation.Scoped

@Factory
@Scope(FilmScope::class)
@Scoped
internal interface FilmRepository {

    fun getFilm(id: String): Flow<FilmData>

    suspend fun likeFilm(filmData: FilmData)

    suspend fun dislikeFilm(id: String)
}
package com.stslex.wizard.core.database.sources.source

import com.stslex.wizard.core.database.sources.model.FilmEntity

interface FavouriteFilmDataSource {

    suspend fun getFilm(id: String): FilmEntity?

    suspend fun likeFilm(filmEntity: FilmEntity)

    suspend fun dislikeFilm(id: String)
}
package com.stslex.core.database.sources.source

import com.stslex.core.database.sources.model.FilmEntity

interface FavouriteFilmDataSource {

    suspend fun getFilm(id: String): FilmEntity?

    suspend fun likeFilm(filmEntity: FilmEntity)

    suspend fun dislikeFilm(id: String)
}
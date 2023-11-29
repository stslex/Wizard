package com.stslex.core.database.sources.source

import com.stslex.core.database.sources.model.FilmEntity

class FavouriteFilmDataSourceImpl : FavouriteFilmDataSource {

    override suspend fun getFilm(id: String): FilmEntity? {
        return null
        TODO("Not yet implemented")
    }

    override suspend fun likeFilm(filmEntity: FilmEntity) {
//        TODO("Not yet implemented")
    }

    override suspend fun dislikeFilm(id: String) {
//        TODO("Not yet implemented")
    }
}